package com.university.shophub.backend.services;

import com.university.shophub.backend.models.*;
import com.university.shophub.backend.repositories.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @Mock
    private static UserService userService;
    @Mock
    private static PurchaseRepository purchaseRepository;

    private static PurchaseService purchaseService;

    private final String SELLER_TEST_EMAIL = "TEST_SELLER_EMAIL";

    @BeforeEach
    void beforeEach() {
        purchaseService = new PurchaseService(purchaseRepository, userService);
    }

    @Test
    void shouldGetPurchaseById() {
        // given
        final Purchase expectedPurchase = getPropperPurchase();
        given(purchaseRepository.findById(expectedPurchase.getId())).willReturn(Optional.of(expectedPurchase));

        // when
        final Purchase actualPurchase = purchaseService.getPurchaseById(expectedPurchase.getId());

        // then
        assertEquals(expectedPurchase, actualPurchase);
    }

    @Test
    void shouldThrowExceptionIfPurchaseNotFound() {
        // given
        final String testId = "TEST";
        given(purchaseRepository.findById(testId)).willReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> purchaseService.getPurchaseById(testId));
    }

    @Test
    void shouldSavePurchases() {
        // given
        final Purchase expectedPurchase = getPropperPurchase();
        final List<Purchase> expectedPurchases = List.of(expectedPurchase);
        final List<Product> products = getProducts();
        final User user = getUser();
        final User seller = getSeller();
        given(purchaseRepository.saveAll(anyList())).willReturn(expectedPurchases);
        given(userService.getUserByEmail(SELLER_TEST_EMAIL)).willReturn(seller);
        given(userService.getUserByUsername(seller.getName())).willReturn(seller);

        // when
        List<Purchase> actualPurchases = purchaseService.savePurchases(products, user);

        // then
        assertEquals(expectedPurchases, actualPurchases);
    }

    private Purchase getPropperPurchase() {
        return Purchase.builder()
                .id("TEST_ID")
                .purchaseDate(LocalDate.now())
                .productNames(List.of("Product1", "Product2", "Product3"))
                .userId("TEST_USER_ID")
                .sellerId("TEST_SELLER_ID")
                .sellerName(SELLER_TEST_EMAIL)
                .build();
    }

    private List<Product> getProducts() {
        return List.of(
                Product.builder()
                        .id("TEST_PRODUCT_ID_1")
                        .name("TEST_PRODUCT_NAME_1")
                        .categoryId(1L)
                        .description("TEST_DESCRIPTION")
                        .imageUrl("TEST_URL")
                        .price(BigDecimal.ZERO)
                        .sellerName(SELLER_TEST_EMAIL)
                        .technicalDetails(List.of(new TechnicalDetail("TEST", "TEST")))
                        .build(),
                Product.builder()
                        .id("TEST_PRODUCT_ID_2")
                        .name("TEST_PRODUCT_NAME_2")
                        .categoryId(1L)
                        .description("TEST_DESCRIPTION")
                        .imageUrl("TEST_URL")
                        .price(BigDecimal.ZERO)
                        .sellerName(SELLER_TEST_EMAIL)
                        .technicalDetails(List.of(new TechnicalDetail("TEST", "TEST")))
                        .build(),
                Product.builder()
                        .id("TEST_PRODUCT_ID_2")
                        .name("TEST_PRODUCT_NAME_2")
                        .categoryId(1L)
                        .description("TEST_DESCRIPTION")
                        .imageUrl("TEST_URL")
                        .price(BigDecimal.ZERO)
                        .sellerName(SELLER_TEST_EMAIL)
                        .technicalDetails(List.of(new TechnicalDetail("TEST", "TEST")))
                        .build());
    }

    private User getUser() {
        return User.builder()
                .id("TEST_USER_ID")
                .role(Role.USER)
                .createdAt(LocalDate.of(2024, 4, 21))
                .email("TEST_EMAIL")
                .password("TEST_PASSWORD")
                .build();
    }

    private User getSeller() {
        return User.builder()
                .id("TEST_SELLER_ID")
                .role(Role.SELLER)
                .createdAt(LocalDate.of(2024, 1, 1))
                .email(SELLER_TEST_EMAIL)
                .name("TEST_SELLER_NAME")
                .password("TEST_PASSWORD")
                .build();
    }
}
