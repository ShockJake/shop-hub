package com.university.shophub.backend.preload;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.TechnicalDetail;
import com.university.shophub.backend.repositories.ProductRepository;
import com.university.shophub.backend.services.ProductService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public record ProductDataLoader(ProductService productService, ProductRepository productRepository) implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) {
        productRepository.deleteAll();
        List<Product> initialProducts = List.of(new Product(null,
                        "seller@seller.com",
                        "Mountain bike",
                        "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                                "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                                "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                                "and less efficient on smooth surfaces.",
                        5L, BigDecimal.valueOf(25000),
                        "https://surlybikes.com/uploads/bikes/surly-preamble-flat-bar-bike-blue-BK3643-800x600.jpg",
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "seller@seller.com",
                        "Mountain bike2",
                        "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                                "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                                "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                                "and less efficient on smooth surfaces.",
                        5L, BigDecimal.valueOf(2500),
                        "https://surlybikes.com/uploads/bikes/surly-ogre-bike-fermented-plum-BK00290-800x600.jpg",
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "seller@seller.com",
                        "Mountain bike3",
                        "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                                "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                                "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                                "and less efficient on smooth surfaces.",
                        5L, BigDecimal.valueOf(2500),
                        "https://surlybikes.com/uploads/bikes/surly-preamble-flat-bar-bike-blue-BK3643-800x600.jpg",
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "seller@seller.com",
                        "Mountain bike4",
                        "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                                "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                                "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                                "and less efficient on smooth surfaces.",
                        5L, BigDecimal.valueOf(2500),
                        "https://surlybikes.com/uploads/bikes/surly-ogre-bike-fermented-plum-BK00290-800x600.jpg",
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))));
        productService.saveAll(initialProducts);
    }
}
