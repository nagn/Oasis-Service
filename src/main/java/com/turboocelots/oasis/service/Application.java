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

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    @Bean
    public CommandLineRunner init(OasisUserRepository repository,
                                  RoleRepository roleRepository) {
        return (args) -> {
            // save a couple of users
            repository.save(new OasisUser("Jack", "Bauer"));
            repository.save(new OasisUser("Happy", "Birthday"));
            repository.save(new OasisUser("Oh", "Yeah"));

            // Save Roles
            roleRepository.save(new Role("Administrator",
                    "Special account for maintenance. Can delete accounts, " +
                    "ban a user from submitting reports, and unblock a locked accounts. 4" +
                    "Can also view the Security log."));
            roleRepository.save(new Role("Reporter", "Basic account. Can submit a report on water " +
                    "availability and view available water sources."));
            roleRepository.save(new Role("Worker", "Can report on water purity levels."));
            roleRepository.save(new Role("Manager", "Can view historical reports and trends of " +
                    "water purity. Can also delete inaccurate individual reports."));

        };
    }

}