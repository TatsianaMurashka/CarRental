package com.htp.dao.jdbctemplate;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository("userRepositoryJdbcTemplate")
public class UserRepository implements UserDao {

    public static final String USER_ID = "id";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_PASSPORT_DATA = "passport_data";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CREATED = "created";
    public static final String USER_CHANGED = "changed";
    public static final String USER_LOCATION = "location_id";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_users order by id desc";
        return jdbcTemplate.query(findAllQuery, this::userRowMapper);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(findOne(userId));
    }

    @Override
    public User findOne(Long userId) {
        final String findById = "select * from m_users where id = :userId";
        return namedParameterJdbcTemplate.queryForObject(findById, new MapSqlParameterSource("userId", userId), this::userRowMapper);
    }

    @Override
    public User save(User user) {
        final String insertQuery = "insert into m_users (first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id)\n" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String findLastIdQuery = "SELECT currval('m_customers_id_seq') as last_insert_id";

        Object[] params = new Object[]{user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
                user.getPassportData(), user.getLogin(), user.getPassword(),
                user.getCreated(), user.getChanged(), user.getLocationId()};
        int[] types = new int[]
                {
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.TIMESTAMP,
                        Types.TIMESTAMP,
                        Types.BIGINT,
                };
        jdbcTemplate.update(insertQuery, params, types);

        long lastId = jdbcTemplate.queryForObject(findLastIdQuery, Long.class);

        return findOne(lastId);
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update m_users set first_name = ?, last_name = ?, phone_number = ?, passport_data = ?" +
                "where id = ?";

        Object[] params = new Object[]{user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
                user.getPassportData(), user.getId()};
        int[] types = new int[]
                {
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.BIGINT,
                };
        jdbcTemplate.update(updateQuery, params, types);

        return findOne(user.getId());
    }

    @Override
    public int delete(Long userId) {
        final String deleteQuery = "delete from m_users where id = ?";
        return jdbcTemplate.update(deleteQuery, userId);
    }

    private User userRowMapper(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(USER_ID));
        user.setFirstName(resultSet.getString(USER_FIRST_NAME));
        user.setLastName(resultSet.getString(USER_LAST_NAME));
        user.setPhoneNumber(resultSet.getString(USER_PHONE_NUMBER));
        user.setPassportData(resultSet.getString(USER_PASSPORT_DATA));
        user.setLogin(resultSet.getString(USER_LOGIN));
        user.setPassword(resultSet.getString(USER_PASSWORD));
        user.setCreated(resultSet.getTimestamp(USER_CREATED));
        user.setChanged(resultSet.getTimestamp(USER_CHANGED));
        user.setLocationId(resultSet.getLong(USER_LOCATION));
        return user;
    }

}
