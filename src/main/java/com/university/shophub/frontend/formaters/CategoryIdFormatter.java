package com.university.shophub.frontend.formaters;

import com.university.shophub.backend.services.CategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryIdFormatter implements Formatter<Long> {

    private final CategoryService categoryService;

    public CategoryIdFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public @NotNull Long parse(@NotNull String text, @NotNull Locale locale) throws ParseException {
        try {

            return categoryService.getCategoryByName(text).getId();
        } catch (NullPointerException e) {
            try {
                return Long.parseLong(text);
            } catch (NumberFormatException ex) {
                throw new ParseException("No category found with given name: " + text, 0);
            }
        }
    }

    @Override
    public @NotNull String print(@NotNull Long object, @NotNull Locale locale) {
        try {
            return categoryService.getCategoryById(object).getName();
        } catch (NullPointerException e) {
            return String.valueOf(object);
        }
    }
}
