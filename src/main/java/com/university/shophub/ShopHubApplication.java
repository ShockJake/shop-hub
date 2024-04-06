package com.university.shophub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ShopHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopHubApplication.class, args);
    }

}
