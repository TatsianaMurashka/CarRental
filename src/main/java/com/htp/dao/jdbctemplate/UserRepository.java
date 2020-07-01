package com.htp.dao.jdbctemplate;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public static final String USER_IS_DELETED = "is_deleted";

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
    public List<User> search(String searchParam) {
        final String searchQuery = "select * from m_users where login = ? order by id desc";
        return jdbcTemplate.query(searchQuery, this::userRowMapper, searchParam);
    }

    @Override
    public User save(User user) {
        final String insertQuery = "insert into m_users (first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted)\n" +
                " values (:first_name, :last_name, :phone_number, :passport_data, :login, :password, :created, :changed, :location_id, :is_deleted)";
        final String findLastIdQuery = "SELECT currval('m_users_id_seq') as last_insert_id";

        namedParameterJdbcTemplate.update(insertQuery, new MapSqlParameterSource("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("phone_number", user.getPhoneNumber())
                .addValue("passport_data", user.getPassportData())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("created", user.getCreated())
                .addValue("changed", user.getChanged())
                .addValue("location_id", user.getLocationId())
                .addValue("is_deleted", user.isDeleted()));

        long lastId = jdbcTemplate.queryForObject(findLastIdQuery, Long.class);

        return findOne(lastId);
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update m_users set first_name = :first_name, last_name = :last_name, phone_number = :phone_number, passport_data = :passport_data" +
                " where id = :id";

        namedParameterJdbcTemplate.update(updateQuery, new MapSqlParameterSource("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("phone_number", user.getPhoneNumber())
                .addValue("passport_data", user.getPassportData())
                .addValue("id", user.getId()));

        return findOne(user.getId());
    }

    @Override
    public int delete(Long userId) {
        final String deleteQuery = "update m_users set is_deleted = true where id = ?";
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
        user.setDeleted(resultSet.getBoolean(USER_IS_DELETED));
        user.setDeleted(resultSet.getBoolean(USER_IS_DELETED));
        return user;
    }

}
