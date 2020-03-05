package com.carousell.marketplace.service;

import com.carousell.marketplace.dao.Category;
import com.carousell.marketplace.dao.Listing;
import com.carousell.marketplace.dao.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
public interface ListingService {

    Listing createListing(
        String title,
        String description,
        BigDecimal price,
        User user,
        Category category
    );

    void deleteListing(
        Long listingId,
        User user
    );

    Listing getListing(
        Long listingId
    );

    List<Listing> getListingByCategory(
        Category category,
        String sortBy,
        String sortDirection
    );

    String getTopCategory();
}
