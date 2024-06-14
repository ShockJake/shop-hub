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
        List<Product> initialProducts = List.of(new Product(null,
                        "seller@seller.com",
                        "Mountain bike",
                        "A mountain bike (MTB) or mountain bicycle is a bicycle designed for off-road cycling. " +
                                "Mountain bikes share some similarities with other bicycles, but incorporate features designed " +
                                "to enhance durability and performance in rough terrain, which makes them heavier, more complex " +
                                "and less efficient on smooth surfaces.",
                        5L, BigDecimal.valueOf(10000),
                        "https://surlybikes.com/uploads/bikes/surly-preamble-flat-bar-bike-blue-BK3643-800x600.jpg",
                        2L,
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
                        5L,
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
                        5L,
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
                        5L,
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description"))),
                new Product(null,
                        "seller@seller.com",
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
                        "seller@seller.com",
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
                        "seller@seller.com",
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
                        "seller@seller.com",
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
                        "seller@seller.com",
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
                        "seller@seller.com",
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
                        "seller@seller.com",
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
                        "seller@seller.com",
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
                        "seller@seller.com",
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
                        List.of(new TechnicalDetail("tech1", "description"), new TechnicalDetail("tech2", "description"), new TechnicalDetail("tech3", "description")))
        );
        productService.saveAll(initialProducts);
    }
}
