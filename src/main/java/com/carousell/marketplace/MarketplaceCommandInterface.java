package com.carousell.marketplace;

import com.carousell.marketplace.annotation.ExceptionHandler;
import com.carousell.marketplace.dto.ListingDto;
import com.carousell.marketplace.facade.ListingFacade;
import com.carousell.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.util.List;

import static com.carousell.marketplace.utils.Constants.*;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
@ShellComponent
public class MarketplaceCommandInterface {

    @Autowired
    private UserService service;

    @Autowired
    private ListingFacade listingFacade;

    @ExceptionHandler
    @ShellMethod(key = REGISTER, value = "Register a new user")
    public String register(
        @ShellOption String username
    ) {
        service.registerUser(username);
        return SUCCESS;
    }

    @ExceptionHandler
    @ShellMethod(key = CREATE_LISTING, value = "Create a listing")
    public Long createListing(
        @ShellOption String username,
        @ShellOption String title,
        @ShellOption String description,
        @ShellOption BigDecimal price,
        @ShellOption String category
    ) {
        ListingDto listingDto = listingFacade
            .createListing(
                username,
                title,
                description,
                price,
                category
            );
        return listingDto.getId();
    }

    @ExceptionHandler
    @ShellMethod(key = DELETE_LISTING, value = "Delete listings")
    public String deleteListing(
        @ShellOption String username,
        @ShellOption Long listingId
    ) {
        listingFacade
            .deleteListing(username, listingId);
        return SUCCESS;
    }

    @ExceptionHandler
    @ShellMethod(key = GET_LISTING, value = "Get listings")
    public ListingDto getListing(
        @ShellOption String username,
        @ShellOption Long listingId
    ) {
        return listingFacade
            .getListing(username, listingId);
    }

    @ExceptionHandler
    @ShellMethod(key = GET_CATEGORY, value = "Get listings for category")
    public List<ListingDto> getCategory(
        @ShellOption String username,
        @ShellOption String category,
        @ShellOption String sortBy,
        @ShellOption String sortDirection
    ) {
        return listingFacade
            .getListingByCategory(
                username,
                category,
                sortBy,
                sortDirection
            );
    }

    @ExceptionHandler
    @ShellMethod(key = GET_TOP_CATEGORY, value = "Get listings")
    public String getTopCategory(
        @ShellOption String username
    ) {
        return listingFacade
            .getTopCategory(username);
    }

}

//REGISTER f
//CREATE_LISTING f t1 d1 11 c2
//GET_TOP_CATEGORY f
//tes
