package com.htp.controller;

import com.htp.dao.*;
import com.htp.domain.CarModel;
import com.htp.domain.Location;
import com.htp.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class FrontController extends HttpServlet {

    public static final String FIND_ONE = "findOne";
    public static final String FIND_BY_ID = "findById";
    public static final String FIND_ALL = "findAll";
    public static final String SAVE = "save";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    private UserDao userDao = new UserDaoImpl();
    private CarModelDao carDao = new CarModelDaoImpl();
    private LocationDao locationDao = new LocationDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doUser(HttpServletRequest req, String userSearchQuery, HttpServletResponse resp) throws ServletException, IOException {
        String typeOfSearch = StringUtils.isNotBlank(req.getParameter("type")) ? req.getParameter("type") : "0";
        String userName = StringUtils.isNotBlank(req.getParameter("userName")) ? req.getParameter("userName") : "0";

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
            if (dispatcher != null) {

                String result = "";

                switch (typeOfSearch) {
                    case FIND_ONE:
                        result = userDao.findOne(Long.parseLong(userSearchQuery)).getPassportData();
                        break;
                    case FIND_BY_ID:
                        Optional<User> optionalUser = userDao.findById(Long.parseLong(userSearchQuery));
                        if (optionalUser.isPresent()) {
                            result = optionalUser.get().getPassportData();
                        }
                        break;
                    case SAVE:
                        User user = new User();
                        user.setFirstName(userName);
                        user.setLastName(userName);
                        user.setPhoneNumber(UUID.randomUUID().toString());
                        user.setPassportData(UUID.randomUUID().toString());
                        user.setLogin(UUID.randomUUID().toString());
                        user.setPassword(UUID.randomUUID().toString());
                        user.setCreated(new Timestamp(new Date().getTime()));
                        user.setChanged(new Timestamp(new Date().getTime()));
                        user.setLocationId(4L);

                        result = userDao.save(user).getPassportData();
                        break;
                    case UPDATE:
                        User userForUpdate = userDao.findOne(Long.parseLong(userSearchQuery));
                        userForUpdate.setFirstName(userName);

                        result = userDao.update(userForUpdate).getPassportData();
                        break;
                    case DELETE:
                        result = "Delete Result: " + userDao.delete(Long.parseLong(userSearchQuery));
                        break;
                    case FIND_ALL:
                    default:
                        result = userDao.findAll().stream().map(User::getFirstName).collect(Collectors.joining(","));
                        break;
                }

                req.setAttribute("userNames", result);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
            req.setAttribute("errors", e.getMessage());
            dispatcher.forward(req, resp);
        }
    }

    private void doCar(HttpServletRequest req, String carSearchQuery, HttpServletResponse resp) throws ServletException, IOException {
        String carTypeOfSearch = StringUtils.isNotBlank(req.getParameter("type")) ? req.getParameter("type") : "0";
        String carModelStr = StringUtils.isNotBlank(req.getParameter("carModel")) ? req.getParameter("carModel") : "0";
        String carBrandStr = StringUtils.isNotBlank(req.getParameter("carBrand")) ? req.getParameter("carBrand") : "0";

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
            if (dispatcher != null) {

                String result = "";

                switch (carTypeOfSearch) {
                    case FIND_ONE:
                        result = carDao.findOne(Long.parseLong(carSearchQuery)).getModel();
                        break;
                    case FIND_BY_ID:
                        Optional<CarModel> optionalCarModel = carDao.findById(Long.parseLong(carSearchQuery));
                        if (optionalCarModel.isPresent()) {
                            result = optionalCarModel.get().getModel();
                        }
                        break;
                    case SAVE:
                        CarModel carModel = new CarModel();

                        carModel.setBrand(carBrandStr);
                        carModel.setModel(carModelStr);
                        carModel.setColor("Red");
                        carModel.setCapacity(new Random().nextInt(4) + 2);
                        carModel.setTransmission("");
                        carModel.setFuelType("");

                        result = carDao.save(carModel).getBrand();
                        break;
                    case UPDATE:
                        CarModel carForUpdate = carDao.findOne(Long.parseLong(carSearchQuery));
                        carForUpdate.setModel(carModelStr);

                        result = carDao.update(carForUpdate).getModel();
                        break;
                    case DELETE:
                        CarModel carForDelete = carDao.findOne(Long.parseLong(carSearchQuery));

                        result = "Delete Result: " + carDao.delete(carForDelete);
                        break;
                    case FIND_ALL:
                    default:
                        result = carDao.findAll().stream().map(CarModel::getBrand).collect(Collectors.joining(","));
                        break;
                }

                req.setAttribute("carNames", result);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
            req.setAttribute("errors", e.getMessage());
            dispatcher.forward(req, resp);
        }
    }

    private void doLocation(HttpServletRequest req, String locationSearchQuery, HttpServletResponse resp) throws ServletException, IOException {
        String type = StringUtils.isNotBlank(req.getParameter("type")) ? req.getParameter("type") : "0";
        String country = StringUtils.isNotBlank(req.getParameter("country")) ? req.getParameter("country") : "0";
        String city = StringUtils.isNotBlank(req.getParameter("city")) ? req.getParameter("city") : "0";

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
            if (dispatcher != null) {

                String result = "";

                switch (type) {
                    case FIND_ONE:
                        result = locationDao.findOne(Long.parseLong(locationSearchQuery)).getCity();
                        break;
                    case FIND_BY_ID:
                        Optional<Location> optionalLocation = locationDao.findById(Long.parseLong(locationSearchQuery));
                        if (optionalLocation.isPresent()) {
                            result = optionalLocation.get().getCity();
                        }
                        break;
                    case SAVE:
                        Location location = new Location();
                        location.setCountry(country);
                        location.setCity(city);
                        location.setStreet("");
                        location.setHouse("");
                        location.setApartment("");

                        result = locationDao.save(location).getCity();
                        break;
                    case UPDATE:
                        Location locationForUpdate = locationDao.findOne(Long.parseLong(locationSearchQuery));
                        locationForUpdate.setCity(city);

                        result = locationDao.update(locationForUpdate).getCity();
                        break;
                    case DELETE:
                        result = "Delete Result: " + locationDao.delete(Long.parseLong(locationSearchQuery));
                        break;
                    case FIND_ALL:
                    default:
                        result = locationDao.findAll().stream().map(Location::getCity).collect(Collectors.joining(","));
                        break;
                }

                req.setAttribute("locationNames", result);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
            req.setAttribute("errors", e.getMessage());
            dispatcher.forward(req, resp);
        }
    }

    private void doBatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
            if (dispatcher != null) {
                String jsonStr = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                JSONObject jObject = new JSONObject(jsonStr);
                JSONArray carsjArray = jObject.getJSONArray("cars");
                int length = carsjArray.length();
                List<CarModel> carModels = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    JSONObject car = carsjArray.getJSONObject(i);

                    CarModel carModel = new CarModel();

                    carModel.setBrand(car.getString("brand"));
                    carModel.setModel(car.getString("model"));
                    carModel.setColor("Red");
                    carModel.setCapacity(new Random().nextInt(4) + 2);
                    carModel.setTransmission("");
                    carModel.setFuelType("");

                    carModels.add(carModel);
                }
                carDao.saveBatch(carModels);

                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
            req.setAttribute("errors", e.getMessage());
            dispatcher.forward(req, resp);
        }
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String batchCommandType = StringUtils.isNotBlank(req.getParameter("commandType")) ? req.getParameter("commandType") : "0";
        if (batchCommandType != "0") {
            doBatch(req, resp);
            return;
        }

        String userSearchQuery = StringUtils.isNotBlank(req.getParameter("userId")) ? req.getParameter("userId") : "0";
        String carSearchQuery = StringUtils.isNotBlank(req.getParameter("carId")) ? req.getParameter("carId") : "0";
        String locationSearchQuery = StringUtils.isNotBlank(req.getParameter("locationId")) ? req.getParameter("locationId") : "0";

        if (userSearchQuery != "0") {
            doUser(req, userSearchQuery, resp);
        } else if (carSearchQuery != "0") {
            doCar(req, carSearchQuery, resp);
        } else if (locationSearchQuery != "0") {
            doLocation(req, locationSearchQuery, resp);
        } else {
            throw new RuntimeException("Exception!");
        }

    }
}