package com.htp.dao;

import com.htp.domain.CarModel;
import com.htp.exeptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;

public class CarModelDaoImpl implements CarModelDao {
    public static DatabaseConfiguration config = DatabaseConfiguration.getInstance();

    public static final String CAR_MODEL_ID = "id";
    public static final String CAR_BRAND = "brand";
    public static final String CAR_MODEL_NAME = "model";
    public static final String CAR_COLOR = "color";
    public static final String CAR_CAPACITY = "capacity";
    public static final String CAR_TRANSMISSION = "transmission";
    public static final String CAR_FUEL_TYPE = "fuel_type";

    @Override
    public List<CarModel> findAll() {
        final String findAllQuery = "select * from m_car_model order by id desc";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        List<CarModel> resultList = new ArrayList<>();
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
    public Optional<CarModel> findById(Long carModelId) {
        return Optional.ofNullable(findOne(carModelId));
    }

    @Override
    public CarModel findOne(Long carModelId) {
        final String findByIdQuery = "select * from m_car_model where id = ?";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        CarModel carModel = null;
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery);
        ) {
            preparedStatement.setLong(1, carModelId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                carModel = parseResultSet(resultSet);
            } else {
                throw new ResourceNotFoundException("Car model with id " + carModelId + "not found");
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
        return carModel;
    }

    @Override
    public void saveBatch(List<CarModel> carModels) {
        final String insertQuery = "insert into m_car_model (brand, model, color, capacity, transmission, fuel_type)\n" +
                " values (?, ?, ?, ?, ?, ?)";

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
        ) {
            for (CarModel carModel : carModels) {
                preparedStatement.setString(1, carModel.getBrand());
                preparedStatement.setString(2, carModel.getModel());
                preparedStatement.setString(3, carModel.getColor());
                preparedStatement.setInt(4, carModel.getCapacity());
                preparedStatement.setString(5, carModel.getTransmission());
                preparedStatement.setString(6, carModel.getFuel_type());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }


    @Override
    public CarModel save(CarModel carModel) {
        final String insertQuery = "insert into m_car_model (brand, model, color, capacity, transmission, fuel_type)\n" +
                " values (?, ?, ?, ?, ?, ?)";

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
             PreparedStatement lastInsertId = connection.prepareStatement("SELECT currval('car_model_id_seq') as last_insert_id;")
        ) {
            preparedStatement.setString(1, carModel.getBrand());
            preparedStatement.setString(2, carModel.getModel());
            preparedStatement.setString(3, carModel.getColor());
            preparedStatement.setInt(4, carModel.getCapacity());
            preparedStatement.setString(5, carModel.getTransmission());
            preparedStatement.setString(6, carModel.getFuel_type());

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
    public CarModel update(CarModel carModel) {
        final String updateQuery = "update m_car_model set brand = ?, model = ?, color = ?, capacity = ?, " +
                "transmission = ?, fuel_type = ?" +
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
            preparedStatement.setString(1, carModel.getBrand());
            preparedStatement.setString(2, carModel.getModel());
            preparedStatement.setString(3, carModel.getColor());
            preparedStatement.setInt(4, carModel.getCapacity());
            preparedStatement.setString(5, carModel.getTransmission());
            preparedStatement.setString(6, carModel.getFuel_type());

            preparedStatement.setLong(7, carModel.getId());

            preparedStatement.executeUpdate();

            return findOne(carModel.getId());

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!: " + e.getMessage(), e);
        }
    }

    @Override
    public int delete(CarModel carModel) {
        final String deleteQuery = "delete from m_car_model where id = ?";

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
            preparedStatement.setLong(1, carModel.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!: " + e.getMessage(), e);
        }
    }

    private CarModel parseResultSet(ResultSet resultSet) throws SQLException {
        CarModel carModel = new CarModel();
        carModel.setId(resultSet.getLong(CAR_MODEL_ID));
        carModel.setBrand(resultSet.getString(CAR_BRAND));
        carModel.setModel(resultSet.getString(CAR_MODEL_NAME));
        carModel.setColor(resultSet.getString(CAR_COLOR));
        carModel.setCapacity(resultSet.getInt(CAR_CAPACITY));
        carModel.setTransmission(resultSet.getString(CAR_TRANSMISSION));
        carModel.setFuel_type(resultSet.getString(CAR_FUEL_TYPE));
        return carModel;
    }
}
