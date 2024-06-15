package com.university.shophub.backend.preload;

import com.university.shophub.backend.models.Category;
import com.university.shophub.backend.services.CategoryService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record CategoryDataLoader(CategoryService categoryService) implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Category> initialCategories = List.of(new Category(1L, "Fashion"),
                new Category(2L, "Wealth"),
                new Category(3L, "Home"),
                new Category(4L, "Garden"),
                new Category(5L, "Sport"),
                new Category(6L, "Music"));
        categoryService.saveAll(initialCategories);
    }
}
