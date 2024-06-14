package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.Category;
import com.university.shophub.backend.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public record CategoryControllerAPI(CategoryService categoryService) {


    @PatchMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestParam(name = "newName") String name) {
        log.debug("Updating category with id {}, new category name - {}", id, name);
        final Category category = categoryService.getCategoryById(id);
        category.setName(name);
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        log.debug("Deleting category with id {}", id);
        categoryService.deleteCategoryById(id);
    }

    @ModelAttribute
    public Category getCategory() {
        return new Category(null, null);
    }

    @PutMapping("/create")
    public Category createCategory(@RequestBody Category category) {
        log.debug("Creating category {}", category);
        return categoryService.saveCategory(category);
    }
}
