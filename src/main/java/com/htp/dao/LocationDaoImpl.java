package com.htp.dao;

import com.htp.domain.Location;
import com.htp.exeptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;

@Component("locationDaoImpl")
public class LocationDaoImpl implements LocationDao {
    public static DatabaseConfiguration config = DatabaseConfiguration.getInstance();

    public static final String LOCATION_ID = "id";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String HOUSE = "house";
    public static final String APARTMENT = "apartment";

    @Override
    public List<Location> findAll() {
        final String findAllQuery = "select * from m_location order by id desc";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        List<Location> resultList = new ArrayList<>();
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
    public Optional<Location> findById(Long locationId) {
        return Optional.ofNullable(findOne(locationId));
    }

    @Override
    public Location findOne(Long locationId) {
        final String findByIdQuery = "select * from m_location where id = ?";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        Location location = null;
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery);
        ) {
            preparedStatement.setLong(1, locationId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                location = parseResultSet(resultSet);
            } else {
                throw new ResourceNotFoundException("Location with id " + locationId + "not found");
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
        return location;
    }

    @Override
    public Location save(Location location) {
        final String insertQuery = "insert into m_location (country, city, street, house, apartment)\n" +
                " values (?, ?, ?, ?, ?)";

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
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             PreparedStatement lastInsertId = connection.prepareStatement("SELECT currval('m_location_id_seq') as last_insert_id;")
        ) {
            preparedStatement.setString(1, location.getCountry());
            preparedStatement.setString(2, location.getCity());
            preparedStatement.setString(3, location.getStreet());
            preparedStatement.setString(4, location.getHouse());
            preparedStatement.setString(5, location.getApartment());

            preparedStatement.executeUpdate();

            ResultSet set = lastInsertId.executeQuery();
            set.next();
            long insertedCarModelId = set.getInt("last_insert_id");
            return findOne(insertedCarModelId);

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public Location update(Location location) {
        final String updateQuery = "update m_location set country = ?, city = ?, street = ?, house = ?, apartment = ?" +
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
            preparedStatement.setString(1, location.getCountry());
            preparedStatement.setString(2, location.getCity());
            preparedStatement.setString(3, location.getStreet());
            preparedStatement.setString(4, location.getHouse());
            preparedStatement.setString(5, location.getApartment());

            preparedStatement.setLong(6, location.getId());

            preparedStatement.executeUpdate();

            return findOne(location.getId());

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!: " + e.getMessage(), e);
        }
    }

    @Override
    public int delete(Long locationId) {
        final String deleteQuery = "delete from m_location where id = ?";

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
            preparedStatement.setLong(1, locationId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!: " + e.getMessage(), e);
        }
    }

    private Location parseResultSet(ResultSet resultSet) throws SQLException {
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
