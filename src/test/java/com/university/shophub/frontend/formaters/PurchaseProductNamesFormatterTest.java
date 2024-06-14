package com.university.shophub.frontend.formaters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PurchaseProductNamesFormatterTest {

    private final PurchaseProductNamesFormatter formatter = new PurchaseProductNamesFormatter();

    @Test
    void shouldFormatPurchaseProductNames() {
        // given
        final List<String> productNames = List.of("product1", "product2", "product3", "product4");
        final String expectedString = "product1,product2,product3,product4";

        // when
        final String actualString = formatter.print(productNames, Locale.getDefault());

        // then
        assertEquals(expectedString, actualString);
    }

    @Test
    void shouldParsePurchaseProductNames() throws ParseException {
        // given
        final String input = "product1,product2,product3,product4";
        final List<String> expectedProductNames = List.of("product1", "product2", "product3", "product4");


        // when
        final List<String> actualProductNames = formatter.parse(input, Locale.getDefault());

        // then
        assertEquals(expectedProductNames, actualProductNames);
    }
}
