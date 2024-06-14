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
public record ProductDataLoader(ProductService productService,
                                ProductRepository productRepository) implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) {
        productRepository.deleteAll();
        List<Product> initialProducts = List.of(
                new Product(null,
                        "seller@seller.com",
                        "Mountain bike",
                        "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                                "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                                "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                                "and less efficient on smooth surfaces.",
                        5L,
                        BigDecimal.valueOf(10000),
                        "https://surlybikes.com/uploads/bikes/surly-preamble-flat-bar-bike-blue-BK3643-800x600.jpg",
                        2L,
                        List.of(new TechnicalDetail("tech1", "description"),
                                new TechnicalDetail("tech2", "description"),
                                new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "seller@seller.com",
                        "Mountain bike",
                        "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                                "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                                "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                                "and less efficient on smooth surfaces.",
                        5L,
                        BigDecimal.valueOf(2500),
                        "https://surlybikes.com/uploads/bikes/surly-ogre-bike-fermented-plum-BK00290-800x600.jpg",
                        5L,
                        List.of(new TechnicalDetail("tech1", "description"),
                                new TechnicalDetail("tech2", "description"),
                                new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "seller@seller.com",
                        "Mountain bike",
                        "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                                "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                                "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                                "and less efficient on smooth surfaces.",
                        5L,
                        BigDecimal.valueOf(2500),
                        "https://surlybikes.com/uploads/bikes/surly-preamble-flat-bar-bike-blue-BK3643-800x600.jpg",
                        5L,
                        List.of(new TechnicalDetail("tech1", "description"),
                                new TechnicalDetail("tech2", "description"),
                                new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "seller@seller.com",
                        "Mountain bike",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent mollis gravida diam ut convallis." +
                                " Sed sed pellentesque purus, sit amet ullamcorper felis. Vivamus vulputate, leo sit amet congue venenatis," +
                                " lectus lectus scelerisque erat, non convallis nisl nulla sit amet ante. Nulla venenatis, est vitae molestie " +
                                "dictum, justo ante pharetra tellus, vel dictum mi tellus ac erat. Duis a metus lectus. Pellentesque " +
                                "interdum, arcu eget pretium feugiat, sem metus pellentesque urna, pharetra efficitur urna velit ut dolor." +
                                " Vestibulum condimentum iaculis leo. Sed non efficitur nisl, eget pretium nibh. ",
                        5L,
                        BigDecimal.valueOf(2500),
                        "https://surlybikes.com/uploads/bikes/surly-ogre-bike-fermented-plum-BK00290-800x600.jpg",
                        5L,
                        List.of(new TechnicalDetail("tech1", "description"),
                                new TechnicalDetail("tech2", "description"),
                                new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "seller@seller.com",
                        "SAMSUNG QE55Q77C 55 QLED",
                        "Dive into a huge collection of 4K films, TV shows, and all your catch-up TV apps – including " +
                                "Netflix, Disney+, Apple TV and NOW apps*** – all in one place. Not sure what to watch? Our easy-to-use and customisable Smart Hub platform gives you personalised recommendations.",
                        3L,
                        BigDecimal.valueOf(1200),
                        "https://image-us.samsung.com/SamsungUS/home/television-home-theater/tvs/samsung-neo-qled-4k/04232024/NeoQLED4K_QN85QN85DBFXZA_V01.jpg?$product-details-jpg$",
                        10L,
                        List.of(new TechnicalDetail("Quantum Matrix Technology", "Precisely controlled Mini LEDs create an incredibly sharp picture"),
                                new TechnicalDetail("Object Tracking Sound Lite & Dolby Atmos®", "Experience sound that moves with the action on-screen"),
                                new TechnicalDetail("NQ4 AI Gen2 Processor", "Brightness, colour and detail boosted by our most intelligent 4K AI processor"),
                                new TechnicalDetail("Screen size", "55\""))),
                new Product(null,
                        "seller@seller.com",
                        "SAMSUNG QE55Q80 55 QLED",
                        "Dive into a huge collection of 4K films, TV shows, and all your catch-up TV apps – including " +
                                "Netflix, Disney+, Apple TV and NOW apps*** – all in one place. Not sure what to watch? Our easy-to-use and customisable Smart Hub platform gives you personalised recommendations.",
                        3L,
                        BigDecimal.valueOf(1799),
                        "https://images.samsung.com/is/image/samsung/p6pim/pl/qe55q80catxxh/gallery/pl-qled-q80c-qe55q80catxxh-536435034?$650_519_PNG$",
                        10L,
                        List.of(new TechnicalDetail("Quantum Matrix Technology", "Precisely controlled Mini LEDs create an incredibly sharp picture"),
                                new TechnicalDetail("Object Tracking Sound Lite & Dolby Atmos®", "Experience sound that moves with the action on-screen"),
                                new TechnicalDetail("NQ4 AI Gen2 Processor", "Brightness, colour and detail boosted by our most intelligent 4K AI processor"),
                                new TechnicalDetail("Screen size", "55\""))),
                new Product(null,
                        "seller@seller.com",
                        "SAMSUNG QE55Q77C 55 QLED",
                        "Dive into a huge collection of 4K films, TV shows, and all your catch-up TV apps – including " +
                                "Netflix, Disney+, Apple TV and NOW apps*** – all in one place. Not sure what to watch? Our easy-to-use and customisable Smart Hub platform gives you personalised recommendations.",
                        3L,
                        BigDecimal.valueOf(999),
                        "https://images.samsung.com/is/image/samsung/p6pim/pl/qe55q77catxxh/gallery/pl-qled-q70c-qe55q77catxxh-535685782?$650_519_PNG$",
                        10L,
                        List.of(new TechnicalDetail("Quantum Matrix Technology", "Precisely controlled Mini LEDs create an incredibly sharp picture"),
                                new TechnicalDetail("Object Tracking Sound Lite & Dolby Atmos®", "Experience sound that moves with the action on-screen"),
                                new TechnicalDetail("NQ4 AI Gen2 Processor", "Brightness, colour and detail boosted by our most intelligent 4K AI processor"),
                                new TechnicalDetail("Screen size", "55\""))),
                new Product(null,
                        "seller@seller.com",
                        "SAMSUNG QE55Q77C 55 QLED",
                        "Dive into a huge collection of 4K films, TV shows, and all your catch-up TV apps – including " +
                                "Netflix, Disney+, Apple TV and NOW apps*** – all in one place. Not sure what to watch? Our easy-to-use and customisable Smart Hub platform gives you personalised recommendations.",
                        3L,
                        BigDecimal.valueOf(1199),
                        "https://image-us.samsung.com/SamsungUS/home/television-home-theater/tvs/samsung-neo-qled-4k/04232024/NeoQLED4K_QN85QN85DBFXZA_V01.jpg?$product-details-jpg$",
                        10L,
                        List.of(new TechnicalDetail("Quantum Matrix Technology", "Precisely controlled Mini LEDs create an incredibly sharp picture"),
                                new TechnicalDetail("Object Tracking Sound Lite & Dolby Atmos®", "Experience sound that moves with the action on-screen"),
                                new TechnicalDetail("NQ4 AI Gen2 Processor", "Brightness, colour and detail boosted by our most intelligent 4K AI processor"),
                                new TechnicalDetail("Screen size", "55\"")))
        );
        productService.saveAll(initialProducts);
    }
}
