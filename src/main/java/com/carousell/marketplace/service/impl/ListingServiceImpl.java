package com.carousell.marketplace.service.impl;

import com.carousell.marketplace.dao.Category;
import com.carousell.marketplace.dao.Listing;
import com.carousell.marketplace.dao.User;
import com.carousell.marketplace.error.exception.ListingNotFoundException;
import com.carousell.marketplace.error.exception.UserMismatchException;
import com.carousell.marketplace.repository.ListingRepository;
import com.carousell.marketplace.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.carousell.marketplace.error.ErrorType.*;
import static com.carousell.marketplace.utils.Constants.*;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public Listing createListing(
        String title,
        String description,
        BigDecimal price,
        User user,
        Category category
    ) {
        Listing listing = Listing.builder()
            .title(title)
            .description(description)
            .price(price)
            .user(user)
            .category(category)
            .build();
        return listingRepository.save(listing);
    }

    @Override
    public void deleteListing(
        Long listingId,
        User user
        ) {
        Listing listing = listingRepository
            .findById(listingId)
            .orElseThrow(() -> new ListingNotFoundException(
                listingId,
                LISTING_NOT_FOUND_MESSAGE
            ));
        if (!user.getUsername().equals(listing.getUser().getUsername())) {
            throw new UserMismatchException(
                user.getUsername(),
                LISTING_OWNER_MISMATCH_MESSAGE
            );
        }
        listingRepository.delete(listing);
    }

    @Override
    public Listing getListing(
        Long listingId
    ) {
        return findListing(listingId);
    }

    @Override
    public List<Listing> getListingByCategory(
        Category category,
        String sortBy,
        String sortDirection
    ) {
        return listingRepository.findByCategoryId(
            category.getId(),
            getSortOrder(sortBy, sortDirection)
        );
    }

    @Override
    @Cacheable("categories")
    public String getTopCategory() {
        return listingRepository
            .lookUpTopCategory()
            .orElseThrow(() -> new ListingNotFoundException(
                null,
                NOT_FOUND_MESSAGE
            ));
    }

    private Listing findListing(Long listingId) {
        return listingRepository
            .findById(listingId)
            .orElseThrow(() -> new ListingNotFoundException(
                listingId,
                NOT_FOUND_MESSAGE
            ));
    }

    private Sort getSortOrder(String sortBy, String sortDirection) {
        String sortColumn;
        switch (sortBy) {
            case SORT_PRICE:
                sortColumn = PRICE;
                break;
            case SORT_TIME:
                sortColumn = CREATED_AT;
                break;
            default:
                sortColumn = ID;
                break;
        }
        return sortDirection.equals(ASC) ?
            Sort.by(Sort.Direction.ASC, sortColumn) :
            Sort.by(Sort.Direction.DESC, sortColumn);
    }
}
