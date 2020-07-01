package com.htp;

import com.htp.dao.LocationDao;
import com.htp.dao.UserDao;
import com.htp.domain.Location;
import com.htp.domain.User;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

public class JdbcTemplateDemo {
    private static final Logger log = Logger.getLogger(JdbcTemplateDemo.class);

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.htp");
        testUser(context);
        testLocation(context);
    }

    public static void testUser(ApplicationContext context) {
        UserDao userRepositoryJdbcTemplate = (UserDao) context.getBean("userRepositoryJdbcTemplate");

        for (User user : userRepositoryJdbcTemplate.findAll()) {
            System.out.println(user);
        }

        User newUser = userRepositoryJdbcTemplate.save(new User("",
                "",
                "",
                "",
                "",
                "",
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime()),
                4L, false));

        log.info(newUser.getLogin());

        System.out.println(userRepositoryJdbcTemplate.findOne(newUser.getId()));

        User userToUpdate = userRepositoryJdbcTemplate.findOne(newUser.getId());
        userToUpdate.setFirstName("New first name");
        System.out.println(userRepositoryJdbcTemplate.update(userToUpdate));

        System.out.println(userRepositoryJdbcTemplate.delete(newUser.getId()) + " row(s) deleted");
    }

    public static void testLocation(ApplicationContext context) {
        LocationDao locationRepositoryJdbcTemplate = (LocationDao) context.getBean("locationRepositoryJdbcTemplate");

        for (Location location : locationRepositoryJdbcTemplate.findAll()) {
            System.out.println(location);
        }

        Location newLocation = locationRepositoryJdbcTemplate.save(new Location("Country",
                "City",
                "",
                "",
                "", false));

        System.out.println(newLocation);

        System.out.println(locationRepositoryJdbcTemplate.findOne(newLocation.getId()));

        Location locationToUpdate = locationRepositoryJdbcTemplate.findOne(newLocation.getId());
        locationToUpdate.setCity("New City");
        System.out.println(locationRepositoryJdbcTemplate.update(locationToUpdate));

        System.out.println(locationRepositoryJdbcTemplate.delete(newLocation.getId()) + " row(s) deleted");
    }
}
