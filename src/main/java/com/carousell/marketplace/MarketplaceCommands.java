package com.carousell.marketplace;

import com.carousell.marketplace.dao.User;
import com.carousell.marketplace.dto.ListingDto;
import com.carousell.marketplace.error.exception.ApplicationException;
import com.carousell.marketplace.facade.ListingFacade;
import com.carousell.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.util.List;

import static com.carousell.marketplace.utils.Commands.*;
import static com.carousell.marketplace.utils.Constants.SUCCESS;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
@ShellComponent
public class MarketplaceCommands {

    //https://www.baeldung.com/spring-shell-cli
    //https://medium.com/agency04/developing-cli-application-with-spring-shell-part-1-807cd3a32461

    @Autowired
    private UserService service;

    @Autowired
    private ListingFacade listingFacade;

    @ShellMethod(key = REGISTER, value = "Register a new user")
    public String register(
        @ShellOption String username
    ) {
        try {
            service.register(username);
            return SUCCESS;
        } catch (ApplicationException ex) {
            handleException(ex);
            return null;
        }
    }

    @ShellMethod(key = CREATE_LISTING, value = "Create a listing")
    public Long createListing(
        @ShellOption String username,
        @ShellOption String title,
        @ShellOption String description,
        @ShellOption BigDecimal price,
        @ShellOption String category
    ) {
        try {
            ListingDto listingDto = listingFacade
                .createListing(
                    username,
                    title,
                    description,
                    price,
                    category
                );
            return listingDto.getId();
        } catch (ApplicationException ex) {
            handleException(ex);
            return null;
        }
    }

    @ShellMethod(key = DELETE_LISTING, value = "Delete listings")
    public String deleteListing(
        @ShellOption String username,
        @ShellOption Long listingId
    ) {
        try {
            listingFacade
                .deleteListing(username, listingId);
            return SUCCESS;
        } catch (ApplicationException ex) {
            handleException(ex);
            return null;
        }
    }

    @ShellMethod(key = GET_LISTING, value = "Get listings")
    public ListingDto getListing(
        @ShellOption String username,
        @ShellOption Long listingId
    ) {
        try {
            return listingFacade
                .getListing(username, listingId);
        } catch (ApplicationException ex) {
            handleException(ex);
            return null;
        }
    }

    @ShellMethod(key = GET_CATEGORY, value = "Get listings for category")
    public List<ListingDto> getCategory(
        @ShellOption String username,
        @ShellOption String category,
        @ShellOption String sortBy,
        @ShellOption String sortDirection
    ) {
        try {
            return listingFacade
                .getListingByCategory(
                    username,
                    category,
                    sortBy,
                    sortDirection
                );
        } catch (ApplicationException ex) {
            handleException(ex);
            return null;
        }
    }

    @ShellMethod(key = GET_TOP_CATEGORY, value = "Get listings")
    public String getTopCategory(
        @ShellOption String username
    ) {
        try {
            return listingFacade
                .getTopCategory(username);
        } catch (ApplicationException ex) {
            handleException(ex);
            return null;
        }
    }

    @ShellMethod("Get existing user")
    public Long getUser(
        @ShellOption String username
    ) {
        User user = service.getUser(username);
        return user.getId();
    }

    private void handleException(ApplicationException ex) {
        System.out.println(ex.getMessage());
    }

}
