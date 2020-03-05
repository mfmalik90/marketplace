package com.carousell.marketplace.facade;

import com.carousell.marketplace.dao.Listing;
import com.carousell.marketplace.dto.ListingDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
public interface ListingFacade {

    ListingDto createListing(
        String username,
        String title,
        String description,
        BigDecimal price,
        String category
    );

    void deleteListing(
        String username,
        Long listingId
    );

    ListingDto getListing(
        String username,
        Long listingId
    );

    List<ListingDto> getListingByCategory(
        String username,
        String category,
        String sortBy,
        String sortDirection
    );

    String getTopCategory(
      String username
    );
}
