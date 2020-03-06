package com.carousell.marketplace;

import com.carousell.marketplace.dao.Category;
import com.carousell.marketplace.dao.Listing;
import com.carousell.marketplace.dao.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author faizanmalik
 * creation date 3/6/20
 */
public final class TestHelper {
    private TestHelper(){}

    public static final String testUserName = "user1";
    public static final Long testId = 1L;
    public static final String testCategoryName = "category1";
    public static final String testTitle = "title";
    public static final String testDescription = "description";
    public static final BigDecimal testPrice = BigDecimal.TEN;
    public static final String testSortBy = "sort_time";
    public static final String testSortDirection = "ASC";

    public static User mockUser() {
        return User.builder()
            .id(testId)
            .username(testUserName)
            .createdAt(new Date())
            .build();
    }

    public static Category mockCategory() {
        return Category.builder()
            .id(testId)
            .name(testCategoryName)
            .createdAt(new Date())
            .build();
    }

    public static Listing mockListing() {
        return Listing.builder()
            .id(testId)
            .title(testTitle)
            .description(testDescription)
            .price(testPrice)
            .user(mockUser())
            .category(mockCategory())
            .createdAt(new Date())
            .build();
    }
}
