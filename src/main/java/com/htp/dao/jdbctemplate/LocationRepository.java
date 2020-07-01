package com.htp.dao.jdbctemplate;

import com.htp.dao.LocationDao;
import com.htp.domain.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("locationRepositoryJdbcTemplate")
public class LocationRepository implements LocationDao {

    public static final String LOCATION_ID = "id";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String HOUSE = "house";
    public static final String APARTMENT = "apartment";
    public static final String LOCATION_IS_DELETED = "is_deleted";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LocationRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Location> findAll() {
        final String findAllQuery = "select * from m_location order by id desc";
        return jdbcTemplate.query(findAllQuery, this::userRowMapper);
    }

    @Override
    public Optional<Location> findById(Long locationId) {
        return Optional.ofNullable(findOne(locationId));
    }

    @Override
    public Location findOne(Long locationId) {
        final String findById = "select * from m_location where id = :id";
        return namedParameterJdbcTemplate.queryForObject(findById, new MapSqlParameterSource("id", locationId), this::userRowMapper);
    }

    @Override
    public List<Location> search(String searchParam) {
        final String searchQuery = "select * from m_location where city = ? order by id desc";
        return jdbcTemplate.query(searchQuery, this::userRowMapper, searchParam);
    }

    @Override
    public Location save(Location location) {
        final String insertQuery = "insert into m_location (country, city, street, house, apartment, is_deleted)\n" +
                " values (:country, :city, :street, :house, :apartment, :is_deleted)";
        final String findLastIdQuery = "SELECT currval('m_location_id_seq') as last_insert_id";

        namedParameterJdbcTemplate.update(insertQuery, new MapSqlParameterSource("country", location.getCountry())
                .addValue("city", location.getCity())
                .addValue("street", location.getStreet())
                .addValue("house", location.getHouse())
                .addValue("apartment", location.getApartment())
                .addValue("is_deleted", location.isDeleted()));

        long lastId = jdbcTemplate.queryForObject(findLastIdQuery, Long.class);

        return findOne(lastId);
    }

    @Override
    public Location update(Location location) {
        final String updateQuery = "update m_location set city = :city where id = :id";

        namedParameterJdbcTemplate.update(updateQuery, new MapSqlParameterSource("city", location.getCity())
                .addValue("id", location.getId()));

        return findOne(location.getId());
    }

    @Override
    public int delete(Long locationId) {
        final String deleteQuery = "update m_location set is_deleted = true where id = ?";
        return jdbcTemplate.update(deleteQuery, locationId);
    }

    private Location userRowMapper(ResultSet resultSet, int i) throws SQLException {
        Location location = new Location();
        location.setId(resultSet.getLong(LOCATION_ID));
        location.setCountry(resultSet.getString(COUNTRY));
        location.setCity(resultSet.getString(CITY));
        location.setStreet(resultSet.getString(STREET));
        location.setHouse(resultSet.getString(HOUSE));
        location.setApartment(resultSet.getString(APARTMENT));
        location.setDeleted(resultSet.getBoolean(LOCATION_IS_DELETED));
        return location;
    }

}
