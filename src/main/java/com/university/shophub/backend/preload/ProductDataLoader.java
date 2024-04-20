package com.university.shophub.backend.preload;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.services.ProductService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public record ProductDataLoader(ProductService productService) implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Product> initialProducts = List.of(new Product(1L,
                "Mountain bike",
                "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                        "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                        "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                        "and less efficient on smooth surfaces.",
                5L, BigDecimal.valueOf(25000),
                "https://surlybikes.com/uploads/bikes/surly-preamble-flat-bar-bike-blue-BK3643-800x600.jpg",
                Map.of("tech1", "description", "tech2", "description", "tech3", "description")));
        productService.saveAll(initialProducts);
    }
}
