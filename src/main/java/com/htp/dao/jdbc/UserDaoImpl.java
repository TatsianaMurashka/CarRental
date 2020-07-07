package com.htp.dao.jdbc;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import com.htp.exeptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;

public class UserDaoImpl implements UserDao {
    public static DatabaseConfiguration config = DatabaseConfiguration.getInstance();

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

    private DataSource dataSource;

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_users order by id desc";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        List<User> resultList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(findOne(userId));
    }

    @Override
    public Optional<User> findByLogin(String username) {
        return Optional.empty();
    }

    @Override
    public User findOne(Long userId) {
        final String findById = "select * from m_users where id = ?";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findById);
        ) {
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = parseResultSet(resultSet);
            } else {
                throw new ResourceNotFoundException("User with id " + userId + "not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
        return user;
    }

    @Override
    public List<User> search(String searchParam) {
        final String findAllQueryForPrepared = "select * from m_users where id > ? order by id desc";

        List<User> resultList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQueryForPrepared)) {

            preparedStatement.setLong(1, Long.parseLong(searchParam));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultList;
    }


    @Override
    public User save(User user) {
        final String insertQuery = "insert into m_users (first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id)\n" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
                /*3. Get statement from connection*/
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             PreparedStatement lastInsertId = connection.prepareStatement("SELECT currval('m_users_id_seq') as last_insert_id;")
        ) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getPassportData());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setTimestamp(7, user.getCreated());
            preparedStatement.setTimestamp(8, user.getChanged());
            preparedStatement.setLong(9, user.getLocationId());

            preparedStatement.executeUpdate();

            ResultSet set = lastInsertId.executeQuery();
            set.next();
            long insertedUserId = set.getInt("last_insert_id");
            return findOne(insertedUserId);

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update m_users set first_name = ?, last_name = ?, phone_number = ?, passport_data = ?" +
                "where id = ?";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getPassportData());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setTimestamp(7, user.getCreated());
            preparedStatement.setTimestamp(8, user.getChanged());
            preparedStatement.setLong(8, user.getLocationId());
            preparedStatement.setLong(9, user.getId());

            preparedStatement.executeUpdate();

            return findOne(user.getId());

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!: " + e.getMessage(), e);
        }
    }

    @Override
    public int delete(Long userId) {
        final String deleteQuery = "delete from m_users where id = ?";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        ) {
            preparedStatement.setLong(1, userId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!: " + e.getMessage(), e);
        }
    }

    private User parseResultSet(ResultSet resultSet) throws SQLException {
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
        return user;
    }
}
