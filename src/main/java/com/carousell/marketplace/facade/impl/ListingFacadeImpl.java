package com.carousell.marketplace.facade.impl;

import com.carousell.marketplace.dao.Category;
import com.carousell.marketplace.dao.Listing;
import com.carousell.marketplace.dao.User;
import com.carousell.marketplace.dto.ListingDto;
import com.carousell.marketplace.facade.ListingFacade;
import com.carousell.marketplace.service.CategoryService;
import com.carousell.marketplace.service.ListingService;
import com.carousell.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
@Component
public class ListingFacadeImpl implements ListingFacade{

    @Autowired
    private ListingService listingService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public ListingDto createListing(
        String username,
        String title,
        String description,
        BigDecimal price,
        String categoryName
    ) {
        final User user = userService.getUser(username);
        final Category category = categoryService.getOrCreate(categoryName);
        Listing listing = listingService
            .createListing(
                title,
                description,
                price,
                user,
                category
            );
        return ListingDto.fromListing(listing);
    }

    @Override
    @Transactional
    public void deleteListing(
        String username,
        Long listingId
    ) {
        final User user = userService.getUser(username);
        listingService.deleteListing(listingId, user);
    }

    @Override
    @Transactional
    public ListingDto getListing(
        String username,
        Long listingId
    ) {
        userService.getUser(username);
        Listing listing = listingService.getListing(listingId);
        return ListingDto.fromListing(listing);
    }

    @Override
    @Transactional
    public List<ListingDto> getListingByCategory(
        String username,
        String categoryName,
        String sortBy,
        String sortDirection
    ) {
        userService.getUser(username);
        final Category category = categoryService.getCategory(categoryName);
        List<Listing> listings = listingService
            .getListingByCategory(
                category,
                sortBy,
                sortDirection
            );
        return listings.stream()
            .map(ListingDto::fromListing)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String getTopCategory(
        String username
    ) {
        userService.getUser(username);
        return listingService.getTopCategory();
    }
}
