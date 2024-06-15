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
                        "outdoor@shop.com",
                        "Mountain bike Extreme",
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
                        "outdoor@shop.com",
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
                        "outdoor@shop.com",
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
                        "outdoor@shop.com",
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
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "tshirts@shop.com",
                        "Nike T-Shirt",
                        "Elevate your workout with the Nike Men's Dri-FIT T-Shirt. Designed with moisture-wicking technology, " +
                                "this tee keeps you dry and comfortable, even during intense sessions. Its lightweight, breathable " +
                                "fabric and classic fit provide optimal movement and a sleek look, making it perfect for both athletic " +
                                "performance and everyday wear. Featuring the iconic Nike Swoosh, this t-shirt embodies both style and " +
                                "functionality. Ideal for athletes and casual wearers alike, it's a must-have addition to your active wardrobe.",
                        1L,
                        BigDecimal.valueOf(55),
                        "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/1ba298cc-be48-4470-a4b7-18e284e6354c/t-shirt-dla-duzych-q2hmZD.png",
                        50L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "tshirts@shop.com",
                        "Adidas T-Shirt",
                        "Stay cool and stylish with the Adidas Men's Performance T-Shirt. Engineered with moisture-wicking Climalite fabric, " +
                                "this tee ensures you stay dry and comfortable during your workouts. Its lightweight and breathable material " +
                                "provides maximum comfort, while the athletic fit allows for a full range of motion. The classic design, " +
                                "featuring the iconic Adidas logo, makes it perfect for both training sessions and casual wear. Elevate your " +
                                "athletic wardrobe with this versatile and performance-driven t-shirt from Adidas.",
                        1L,
                        BigDecimal.valueOf(65),
                        "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/825d33b9f25a4a69b2baac4600ea0e04_9366/Essentials_Big_Logo_Tee_Czern_GK9120_01_laydown.jpg",
                        70L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "premiumWatches@shop.com",
                        "Rolex watch",
                        "Elevate your style with the Rolex Submariner, an iconic timepiece synonymous with luxury and precision. Crafted from robust " +
                                "stainless steel and featuring a distinctive black dial and bezel, this watch is designed for both elegance and durability. " +
                                "Its automatic movement ensures impeccable timekeeping, while the water resistance up to 300 meters makes it perfect for " +
                                "diving enthusiasts. With its timeless design and unparalleled craftsmanship, the Rolex Submariner is the ultimate symbol of " +
                                "sophistication and status.",
                        2L,
                        BigDecimal.valueOf(9999.99),
                        "https://www.bobswatches.com/rolex-blog/wp-content/uploads/2017/01/Green-Gold-Rolex-GMT-Master.jpg",
                        5L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "homeAndGarden@shop.com",
                        "Lawnmower1",
                        "Achieve a pristine lawn effortlessly with the Honda HRX217VKA Lawn Mower. Featuring a powerful 200cc engine and adjustable cutting heights, " +
                                "this mower ensures a clean, precise cut for any grass type. Its innovative Versamow™ system allows for seamless mulching, bagging, " +
                                "discharge, and leaf shredding. The ergonomic handle and self-propelled design provide maximum comfort and ease of use. Durable and " +
                                "reliable, the Honda HRX217VKA is the perfect solution for maintaining a beautiful, healthy lawn.",
                        4L,
                        BigDecimal.valueOf(849),
                        "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/21/2114165/Kosiarka-spalinowa-NAC-MEX53-G200-HSD-przod-prawy__1.jpg",
                        20L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "homeAndGarden@shop.com",
                        "Lawnmower2",
                        "Achieve a pristine lawn effortlessly with the Honda HRX217VKA Lawn Mower. Featuring a powerful 200cc engine and adjustable cutting heights, " +
                                "this mower ensures a clean, precise cut for any grass type. Its innovative Versamow™ system allows for seamless mulching, bagging, " +
                                "discharge, and leaf shredding. The ergonomic handle and self-propelled design provide maximum comfort and ease of use. Durable and " +
                                "reliable, the Honda HRX217VKA is the perfect solution for maintaining a beautiful, healthy lawn.",
                        4L,
                        BigDecimal.valueOf(949),
                        "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/88/888836/2__1.jpg",
                        20L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "homeAndGarden@shop.com",
                        "Lawnmower3",
                        "Achieve a pristine lawn effortlessly with the Honda HRX217VKA Lawn Mower. Featuring a powerful 200cc engine and adjustable cutting heights, " +
                                "this mower ensures a clean, precise cut for any grass type. Its innovative Versamow™ system allows for seamless mulching, bagging, " +
                                "discharge, and leaf shredding. The ergonomic handle and self-propelled design provide maximum comfort and ease of use. Durable and " +
                                "reliable, the Honda HRX217VKA is the perfect solution for maintaining a beautiful, healthy lawn.",
                        4L,
                        BigDecimal.valueOf(749),
                        "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/21/2141077/GREENSO-TB40S03A-LC-Loncin-skos.jpg",
                        20L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "homeAndGarden@shop.com",
                        "Refrigerator1",
                        "Keep your food fresh and your kitchen organized with the Samsung Family Hub™ Refrigerator. Featuring a spacious 28 cu. ft. capacity and " +
                                "innovative triple cooling system, this fridge ensures optimal temperature and humidity levels for all your groceries. The built-in " +
                                "Family Hub™ touchscreen allows you to manage your calendar, stream music, and even see inside your fridge without opening the door. " +
                                "With adjustable shelves, a sleek stainless steel finish, and energy-efficient operation, the Samsung Family Hub™ Refrigerator combines " +
                                "convenience, style, and advanced technology for the modern home.",
                        3L,
                        BigDecimal.valueOf(3490),
                        "https://cdn.pixabay.com/photo/2016/10/24/21/03/appliance-1767311_640.jpg",
                        10L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "homeAndGarden@shop.com",
                        "Refrigerator2",
                        "Keep your food fresh and your kitchen organized with the Samsung Family Hub™ Refrigerator. Featuring a spacious 28 cu. ft. capacity and " +
                                "innovative triple cooling system, this fridge ensures optimal temperature and humidity levels for all your groceries. The built-in " +
                                "Family Hub™ touchscreen allows you to manage your calendar, stream music, and even see inside your fridge without opening the door. " +
                                "With adjustable shelves, a sleek stainless steel finish, and energy-efficient operation, the Samsung Family Hub™ Refrigerator combines " +
                                "convenience, style, and advanced technology for the modern home.",
                        3L,
                        BigDecimal.valueOf(4490),
                        "https://www.lg.com/ph/images/refrigerators/md05882016/gallery/GR-H432HLHN-iso-right.jpg",
                        10L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "homeAndGarden@shop.com",
                        "Refrigerator3",
                        "Keep your food fresh and your kitchen organized with the Samsung Family Hub™ Refrigerator. Featuring a spacious 28 cu. ft. capacity and " +
                                "innovative triple cooling system, this fridge ensures optimal temperature and humidity levels for all your groceries. The built-in " +
                                "Family Hub™ touchscreen allows you to manage your calendar, stream music, and even see inside your fridge without opening the door. " +
                                "With adjustable shelves, a sleek stainless steel finish, and energy-efficient operation, the Samsung Family Hub™ Refrigerator combines " +
                                "convenience, style, and advanced technology for the modern home.",
                        3L,
                        BigDecimal.valueOf(5490),
                        "https://img.freepik.com/premium-photo/silver-steel-fridge-model-isolated-white-background_124507-67356.jpg",
                        10L,
                        List.of(new TechnicalDetail("tech1", "description"),
                                new TechnicalDetail("tech2", "description"),
                                new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "homeAndGarden@shop.com",
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
                        "homeAndGarden@shop.com",
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
                        "homeAndGarden@shop.com",
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
                        "homeAndGarden@shop.com",
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
                                new TechnicalDetail("Screen size", "55\""))),
                new Product(null,
                        "guitar@shop.com",
                        "Martyn Guitar",
                        "The Martyn Road Series offers an inexpensive entry into the world of the legendary Matin guitars. The series includes guitars in the famous body form OOO, Dreadnought and Grand Performance. The guitar tops are solid, the back and sides are ply.",
                        6L,
                        BigDecimal.valueOf(1000),
                        "https://sc1.musik-produktiv.com/pic-010116129xxl/martin-guitars-d-10e-01.jpg",
                        15L,
                        List.of(new TechnicalDetail("Body Shape", "Dreadnought"),
                                new TechnicalDetail("Material", "Sapele"),
                                new TechnicalDetail("Color", "Natural Brown"),
                                new TechnicalDetail("Finish", "Matt"),
                                new TechnicalDetail("Construction", "Acoustic"))),
                new Product(null,
                        "guitar@shop.com",
                        "Les Paul Classic Honey Burst",
                        "The Modern Les Paul series includes Les Paul guitars with contemporary features such as modern, flat neck profile, comfortable beveled neck/body transition, locking tuners, locking bridge and tailpiece, and split humbuckers. The bodies are chambered to save weight.",
                        6L,
                        BigDecimal.valueOf(3000),
                        "https://sc1.musik-produktiv.com/pic-010119769xxl/epiphone-les-paul-classic-honey-burst.jpg",
                        21L,
                        List.of(new TechnicalDetail("Body Shape", "Dreadnought"),
                                new TechnicalDetail("Material", "Sapele"),
                                new TechnicalDetail("Color", "Natural Brown"),
                                new TechnicalDetail("Finish", "Matt"),
                                new TechnicalDetail("Construction", "Acoustic"))),
                new Product(null,
                        "guitar@shop.com",
                        "Ibanez Artcore AS73G-BKF",
                        "The Ibanez Artcore AS73G is primarily the semi-hollow of choice for sustain-rich and expressive sounds of jazz and blues. However, the agile electric guitar with its now over 15 years of proven semi-resonance construction also knows how to convince in the distorting realms of rock and exploits the full potential of the flexible Classic Elite humbucker assembly.",
                        6L,
                        BigDecimal.valueOf(1700),
                        "https://sc1.musik-produktiv.com/pic-010129212xxl/ibanez-artcore-as73g-bkf.jpg",
                        10L,
                        List.of(new TechnicalDetail("Body Shape", "ES Shape"),
                                new TechnicalDetail("Material", "Basswood"),
                                new TechnicalDetail("Color", "Black Flat"),
                                new TechnicalDetail("Construction", "Electric"))),
                new Product(null,
                        "guitar@shop.com",
                        "Fender CD-60S LH NAT",
                        "The CD-60S LH is one of Fender's most popular models and ideal for players looking for a high-quality, affordable dreadnought with great tone and playability. With its high-quality solid spruce top, easy-to-play neck and mahogany back and sides, the CD-60-S LH is perfect for the couch, the campfire or the coffeehouse - anywhere you want classic Fender playability and tone.",
                        6L,
                        BigDecimal.valueOf(300),
                        "https://sc1.musik-produktiv.com/pic-010110419l/fender-cd-60s-lh-nat-10110419.jpg",
                        17L,
                        List.of(new TechnicalDetail("Body Shape", "Dreadnought"),
                                new TechnicalDetail("Material", "Basswood"),
                                new TechnicalDetail("Color", "Natural Light"),
                                new TechnicalDetail("Construction", "Solid"))),
                new Product(null,
                        "guitar@shop.com",
                        "Fender American Vintage II 1957 Stratocaster Sea Foam Green",
                        "The successful Fender American Vintage series goes into the second round. The Fender® American Vintage II series is not only visually oriented to the historic designs that have written music history. Many period-typical details have also been reproduced exactly: Bodies, Necks, Hardware, and Premium Finishes as well as Pickups that are carefully tuned to the tone of the original model year. Each instrument embodies the authentic character and sound of the original model thanks to traditional Fender craftsmanship.",
                        6L,
                        BigDecimal.valueOf(4200),
                        "https://sc1.musik-produktiv.com/pic-010148653l/fender-american-vintage-ii-1957-stratocaster-sea-foam-green.jpg",
                        21L,
                        List.of(new TechnicalDetail("Body Shape", "ST-Style"),
                                new TechnicalDetail("Material", "Alder"),
                                new TechnicalDetail("Color", "Sea Foam Green"),
                                new TechnicalDetail("Construction", "Solid Body"))),
                new Product(null,
                        "guitar@shop.com",
                        "Fender Player Telecaster MN Butterscotch Blonde",
                        "The Fender Standard Telecaster Made in Mexico was a big hit by Fender in terms of price / performance ratio and quality. Now it's time for a follow-up: the Fender Player Telecaster MN BTB is the overdue upgrade: solid alder body, one-piece maple neck with comfortable modern 'C' profile and newly developed Alinico 5 single coil pickups.",
                        6L,
                        BigDecimal.valueOf(1530),
                        "https://sc1.musik-produktiv.com/pic-010105787l/fender-player-telecaster-mn-butterscotch-blonde.jpg",
        8L,
                List.of(new TechnicalDetail("Body Shape", "ST-Style"),
                        new TechnicalDetail("Material", "Alder"),
                        new TechnicalDetail("Color", "Butterscotch Blonde"),
                        new TechnicalDetail("Construction", "Solid Body")))
        );
        productService.saveAll(initialProducts);
    }
}
