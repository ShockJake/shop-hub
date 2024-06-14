package com.university.shophub.frontend.formaters;

import com.university.shophub.backend.models.Category;
import com.university.shophub.backend.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryIdFormatterTest {

    @Mock
    private static CategoryService categoryService;
    private static CategoryIdFormatter categoryIdFormatter;

    @BeforeEach
    void beforeEach() {
        categoryIdFormatter = new CategoryIdFormatter(categoryService);
    }

    @Test
    void shouldFormatCategoryId() {
        // given
        final Long testCategoryId = 1L;
        final String expectedCategoryName = "TEST";
        final Category category = new Category(testCategoryId, expectedCategoryName);
        given(categoryService.getCategoryById(testCategoryId)).willReturn(category);

        // when
        final String actualCategoryName = categoryIdFormatter.print(testCategoryId, Locale.getDefault());

        // then
        assertEquals(expectedCategoryName, actualCategoryName);
        verify(categoryService).getCategoryById(testCategoryId);
    }

    @Test
    void shouldReturnLongAsStringIfThereIsNoCategory() {
        // given
        final Long testCategoryId = 1L;
        final String expectedCategoryName = "1";
        given(categoryService.getCategoryById(testCategoryId)).willReturn(null);

        // when
        final String actualCategoryName = categoryIdFormatter.print(testCategoryId, Locale.getDefault());

        // then
        assertEquals(expectedCategoryName, actualCategoryName);
        verify(categoryService).getCategoryById(testCategoryId);
    }

    @Test
    void shouldParseCategory() {
        // given
        final String testCategoryName = "TEST";
        final Long expectedCategoryId = 1L;
        final Category category = new Category(expectedCategoryId, testCategoryName);
        given(categoryService.getCategoryByName(testCategoryName)).willReturn(category);

        // when
        AtomicReference<Long> actualCategoryId = new AtomicReference<>();
        assertDoesNotThrow(() -> actualCategoryId.set(categoryIdFormatter.parse(testCategoryName, Locale.getDefault())));

        // then
        assertEquals(expectedCategoryId, actualCategoryId.get());
        verify(categoryService).getCategoryByName(testCategoryName);
    }

    @Test
    void shouldThrowParseExceptionIfCategoryNotFound() {
        // given
        final String testCategoryName = "NON_EXISTING_CATEGORY";
        given(categoryService.getCategoryByName(testCategoryName)).willReturn(null);

        // when & then
        assertThrows(ParseException.class, () -> categoryIdFormatter.parse(testCategoryName, Locale.getDefault()));
    }
}
