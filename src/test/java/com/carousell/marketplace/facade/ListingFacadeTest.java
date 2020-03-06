package com.carousell.marketplace.facade;

import com.carousell.marketplace.dao.Category;
import com.carousell.marketplace.dao.Listing;
import com.carousell.marketplace.dao.User;
import com.carousell.marketplace.dto.ListingDto;
import com.carousell.marketplace.error.exception.CategoryNotFoundException;
import com.carousell.marketplace.error.exception.ListingNotFoundException;
import com.carousell.marketplace.error.exception.UserMismatchException;
import com.carousell.marketplace.error.exception.UserNotFoundException;
import com.carousell.marketplace.facade.impl.ListingFacadeImpl;
import com.carousell.marketplace.service.CategoryService;
import com.carousell.marketplace.service.ListingService;
import com.carousell.marketplace.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static com.carousell.marketplace.TestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author faizanmalik
 * creation date 3/6/20
 */
public class ListingFacadeTest {

    @InjectMocks
    private ListingFacadeImpl listingFacade;

    @Mock
    private ListingService listingService;

    @Mock
    private UserService userService;

    @Mock
    private CategoryService categoryService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UserNotFoundException.class)
    public void createListingUserNotFound(){
        Category category = mockCategory();
        Listing listing = mockListing();
        User user = mockUser();
        when(userService.getUser(testUserName))
            .thenThrow(UserNotFoundException.class);
        when(categoryService.getOrCreate(testCategoryName))
            .thenReturn(category);
        when(listingService.createListing(
            testTitle,
            testDescription,
            testPrice,
            user,
            category
        )).thenReturn(listing);
        listingFacade.createListing(
            testUserName,
            testTitle,
            testDescription,
            testPrice,
            testCategoryName
        );
        verify(userService, times(1))
            .getUser(testUserName);
        verify(categoryService, times(0))
            .getOrCreate(testCategoryName);
        verify(listingService, times(0))
            .createListing(
                testTitle,
                testDescription,
                testPrice,
                user,
                category
            );
    }

    @Test
    public void createListingSuccess(){
        Category category = mockCategory();
        Listing listing = mockListing();
        User user = mockUser();
        when(userService.getUser(testUserName))
            .thenReturn(user);
        when(categoryService.getOrCreate(testCategoryName))
            .thenReturn(category);
        when(listingService.createListing(
            testTitle,
            testDescription,
            testPrice,
            user,
            category
        )).thenReturn(listing);
        ListingDto listingDto = listingFacade.createListing(
            testUserName,
            testTitle,
            testDescription,
            testPrice,
            testCategoryName
        );
        assertNotNull(listingDto);
        assertEquals(testTitle, listingDto.getTitle());
        assertEquals(testDescription, listingDto.getDescription());
        assertEquals(testPrice, listingDto.getPrice());
        assertEquals(testUserName, listingDto.getUsername());
        assertEquals(testCategoryName, listingDto.getCategory());
        verify(userService, times(1))
            .getUser(testUserName);
        verify(categoryService, times(1))
            .getOrCreate(testCategoryName);
        verify(listingService, times(1))
            .createListing(
                testTitle,
                testDescription,
                testPrice,
                user,
                category
            );
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteListingUserNotFound() {
        User user = mockUser();
        when(userService.getUser(testUserName))
            .thenThrow(UserNotFoundException.class);
        listingFacade.deleteListing(testUserName, testId);
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(0))
            .deleteListing(testId, user);
    }

    @Test(expected = ListingNotFoundException.class)
    public void deleteListingListingNotFound() {
        User user = mockUser();
        when(userService.getUser(testUserName))
            .thenReturn(user);
        doThrow(ListingNotFoundException.class)
            .when(listingService).deleteListing(testId, user);
        listingFacade.deleteListing(testUserName, testId);
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(1))
            .deleteListing(testId, user);
    }

    @Test(expected = UserMismatchException.class)
    public void deleteListingOwnerMismatch() {
        User user = mockUser();
        when(userService.getUser(testUserName))
            .thenReturn(user);
        doThrow(UserMismatchException.class)
            .when(listingService).deleteListing(testId, user);
        listingFacade.deleteListing(testUserName, testId);
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(1))
            .deleteListing(testId, user);
    }

    @Test
    public void deleteListingSuccess() {
        User user = mockUser();
        when(userService.getUser(testUserName))
            .thenReturn(user);
        listingFacade.deleteListing(testUserName, testId);
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(1))
            .deleteListing(testId, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void getListingUserNotFound() {
        when(userService.getUser(testUserName))
            .thenThrow(UserNotFoundException.class);
        listingFacade.getListing(testUserName, testId);
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(0))
            .getListing(testId);
    }

    @Test(expected = ListingNotFoundException.class)
    public void getListingNotFound() {
        when(userService.getUser(testUserName))
            .thenReturn(mockUser());
        when(listingService.getListing(testId))
            .thenThrow(ListingNotFoundException.class);
        listingFacade.getListing(testUserName, testId);
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(1))
            .getListing(testId);
    }

    @Test
    public void getListingSuccess() {
        User user = mockUser();
        Listing listing = mockListing();
        when(userService.getUser(testUserName))
            .thenReturn(user);
        when(listingService.getListing(testId))
            .thenReturn(listing);
        ListingDto listingDto = listingFacade.getListing(testUserName, testId);
        assertNotNull(listingDto);
        assertEquals(testTitle, listingDto.getTitle());
        assertEquals(testDescription, listingDto.getDescription());
        assertEquals(testPrice, listingDto.getPrice());
        assertEquals(testUserName, listingDto.getUsername());
        assertEquals(testCategoryName, listingDto.getCategory());
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(1))
            .getListing(testId);
    }

    @Test(expected = UserNotFoundException.class)
    public void getListingByCategoryUserNotFound() {
        when(userService.getUser(testUserName))
            .thenThrow(UserNotFoundException.class);
        listingFacade.getListingByCategory(
            testUserName,
            testCategoryName,
            testSortBy,
            testSortDirection
        );
    }

    @Test(expected = CategoryNotFoundException.class)
    public void getListingByCategoryCategoryNotFound() {
        when(userService.getUser(testUserName))
            .thenReturn(mockUser());
        when(categoryService.getCategory(testCategoryName))
            .thenThrow(CategoryNotFoundException.class);
        listingFacade.getListingByCategory(
            testUserName,
            testCategoryName,
            testSortBy,
            testSortDirection
        );
    }

    @Test
    public void getListingByCategorySuccess() {
        User user = mockUser();
        Category category = mockCategory();
        Listing listing = mockListing();
        when(userService.getUser(testUserName))
            .thenReturn(user);
        when(categoryService.getCategory(testCategoryName))
            .thenReturn(category);
        when(listingService.getListingByCategory(
            category,
            testSortBy,
            testSortDirection
        )).thenReturn(Collections.singletonList(listing));
        List<ListingDto> listingDtoList = listingFacade.getListingByCategory(
            testUserName,
            testCategoryName,
            testSortBy,
            testSortDirection
        );
        assertEquals(1, listingDtoList.size());
        ListingDto listingDto = listingDtoList.get(0);
        assertNotNull(listingDto);
        assertEquals(testTitle, listingDto.getTitle());
        assertEquals(testDescription, listingDto.getDescription());
        assertEquals(testPrice, listingDto.getPrice());
        assertEquals(testUserName, listingDto.getUsername());
        assertEquals(testCategoryName, listingDto.getCategory());
        verify(userService, times(1))
            .getUser(testUserName);
        verify(categoryService, times(1))
            .getCategory(testCategoryName);
        verify(listingService, times(1))
            .getListingByCategory(category, testSortBy, testSortDirection);
    }

    @Test(expected = UserNotFoundException.class)
    public void getTopCategoryUserNotFound() {
        when(userService.getUser(testUserName))
            .thenThrow(UserNotFoundException.class);
        listingFacade.getTopCategory(testUserName);
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(0))
            .getTopCategory();
    }

    @Test
    public void getTopCategorySuccess() {
        when(userService.getUser(testUserName))
            .thenReturn(mockUser());
        when(listingService.getTopCategory())
            .thenReturn(testCategoryName);
        String category = listingFacade.getTopCategory(testUserName);
        assertEquals(testCategoryName, category);
        verify(userService, times(1))
            .getUser(testUserName);
        verify(listingService, times(1))
            .getTopCategory();
    }
}
