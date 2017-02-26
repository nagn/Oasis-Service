package com.turboocelots.oasis.service;

/**
 * Created by mlin on 2/25/17.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        //TODO: eliminate table re-creation once we want data persistence
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users");
        jdbcTemplate.execute("CREATE TABLE Users(" +
                "user_id SERIAL, name VARCHAR(255), password VARCHAR(255), " +
                "email VARCHAR(255), title VARCHAR(255), address VARCHAR(255), " +
                "phone_number VARCHAR(255), created_at TIMESTAMP, updated_at TIMESTAMP, " +
                "banned BOOLEAN)");

        // Populate a bunch of users
        List<Object[]> names = Arrays.asList("John pass", "Jeff pass2", "Josh pass3", "Josh pass4").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        // Use a Java 8 stream to print out each tuple of the list
        names.forEach(name -> log.info(String.format("Inserting User record for %s", name)));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO Users(Name, Password) VALUES (?, ?)", names);

        /*
        log.info("Querying for Users records where name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, name FROM Users WHERE name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));
        */
    }
}