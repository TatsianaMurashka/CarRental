package com.htp.dao.jdbctemplate;

import com.htp.dao.LocationDao;
import com.htp.domain.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
        final String findById = "select * from m_location where id = :locationId";
        return namedParameterJdbcTemplate.queryForObject(findById, new MapSqlParameterSource("locationId", locationId), this::userRowMapper);
    }

    @Override
    public Location save(Location location) {
        final String insertQuery = "insert into m_location (country, city, street, house, apartment)\n" +
                " values (?, ?, ?, ?, ?)";
        final String findLastIdQuery = "SELECT currval('m_location_id_seq') as last_insert_id";

        Object[] params = new Object[]{location.getCountry(), location.getCity(),
                location.getStreet(), location.getHouse(), location.getApartment()};
        int[] types = new int[]
                {
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                };
        jdbcTemplate.update(insertQuery, params, types);

        long lastId = jdbcTemplate.queryForObject(findLastIdQuery, Long.class);

        return findOne(lastId);
    }

    @Override
    public Location update(Location location) {
        final String updateQuery = "update m_location set city = ? where id = ?";

        Object[] params = new Object[]{location.getCity(), location.getId()};
        int[] types = new int[]
                {
                        Types.VARCHAR,
                        Types.BIGINT,
                };
        jdbcTemplate.update(updateQuery, params, types);

        return findOne(location.getId());
    }

    @Override
    public int delete(Long locationId) {
        final String deleteQuery = "delete from m_location where id = ?";
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
        return location;
    }

}
