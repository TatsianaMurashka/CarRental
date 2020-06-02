package com.htp.dao;

import com.htp.domain.User;
import com.htp.exeptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;

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
    public User save(User user) {
        final String insertQuery = "insert into m_users (first_name, last_name, phone_number, passport_data)\n" +
                " values (?, ?, ?, ?)";

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
             PreparedStatement lastInsertId = connection.prepareStatement("SELECT currval('m_customers_id_seq') as last_insert_id;")
        ) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getPassportData());

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
            preparedStatement.setLong(5, user.getId());

            preparedStatement.executeUpdate();

            return findOne(user.getId());

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!: " + e.getMessage(), e);
        }
    }

    @Override
    public int delete(User user) {
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
            preparedStatement.setLong(1, user.getId());
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
        return user;
    }
}
