package com.turboocelots.oasis.service;

/**
 * Created by mlin on 2/25/17.
 */

import com.turboocelots.oasis.service.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.Instant;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    @Bean
    public CommandLineRunner init(OasisUserRepository userRepository, WaterQualityReportsRepository qualityReportsRepository, WaterSourceReportsRepository sourceRepository) {
        return (args) -> {
            // save a couple of users
            userRepository.save(new OasisUser("Jack", "Bauer", "Administrator"));
            userRepository.save(new OasisUser("Happy", "Birthday", "Worker"));
            userRepository.save(new OasisUser("Oh", "Yeah", "Reporter"));

            qualityReportsRepository.save(new WaterQualityReport(
                    Timestamp.from(Instant.now()),
                    "Jack",
                    0.1,
                    0.2,
                    "condition",
                    0.1,
                    0.2));

            sourceRepository.save(new WaterSourceReport(
                    Timestamp.from(Instant.now()),
                    "Dorthy",
                    -84.387982,
                    33.748995,
                    "Good",
                    "Spring"));
            sourceRepository.save(new WaterSourceReport(
                    Timestamp.from(Instant.now()),
                    "Dorthy",
                    -84.4349526,
                    33.7818455,
                    "Bad",
                    "Tap"));
        };
    }

}