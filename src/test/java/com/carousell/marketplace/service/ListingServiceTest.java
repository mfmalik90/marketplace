package com.carousell.marketplace.service;

import com.carousell.marketplace.dao.Listing;
import com.carousell.marketplace.dao.User;
import com.carousell.marketplace.error.exception.ListingNotFoundException;
import com.carousell.marketplace.error.exception.UserMismatchException;
import com.carousell.marketplace.repository.ListingRepository;
import com.carousell.marketplace.service.impl.ListingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.carousell.marketplace.TestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author faizanmalik
 * creation date 3/6/20
 */
public class ListingServiceTest {

    @InjectMocks
    private ListingServiceImpl listingService;

    @Mock
    private ListingRepository listingRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createListingTest() {
        when(listingRepository.save(any()))
            .thenReturn(mockListing());
        Listing listing = listingService
            .createListing(
                testTitle,
                testDescription,
                testPrice,
                mockUser(),
                mockCategory()
            );
        assertNotNull(listing);
        assertEquals(testTitle, listing.getTitle());
        assertEquals(testDescription, listing.getDescription());
        assertEquals(testPrice, listing.getPrice());
    }

    @Test(expected = ListingNotFoundException.class)
    public void deleteListingTestNotFound() {
        when(listingRepository.findById(testId))
            .thenReturn(Optional.empty());
        listingService.deleteListing(testId, mockUser());
        verify(listingRepository, times(1))
            .findById(testId);
        verify(listingRepository, times(0))
            .delete(any());

    }

    @Test(expected = UserMismatchException.class)
    public void deleteListingTestOwnerMismatch() {
        when(listingRepository.findById(testId))
            .thenReturn(Optional.of(mockListing()));
        User requestUser = mockUser();
        requestUser.setUsername("test");
        listingService.deleteListing(testId, requestUser);
        verify(listingRepository, times(1))
            .findById(testId);
        verify(listingRepository, times(0))
            .delete(any());

    }

    @Test
    public void deleteListingTestSuccess() {
        when(listingRepository.findById(testId))
            .thenReturn(Optional.of(mockListing()));
        User requestUser = mockUser();
        listingService.deleteListing(testId, requestUser);
        verify(listingRepository, times(1))
            .findById(testId);
        verify(listingRepository, times(1))
            .delete(any());
    }

    @Test(expected = ListingNotFoundException.class)
    public void getListingTestNotFound() {
        when(listingRepository.findById(testId))
            .thenReturn(Optional.empty());
        listingService.getListing(testId);
    }

    @Test
    public void getListingTestSuccess() {
        when(listingRepository.findById(testId))
            .thenReturn(Optional.of(mockListing()));
        Listing listing = listingService.getListing(testId);
        assertNotNull(listing);
        assertEquals(testTitle, listing.getTitle());
        assertEquals(testDescription, listing.getDescription());
        assertEquals(testPrice, listing.getPrice());
    }

    @Test
    public void getListingByCategoryTestNoRecord() {
        when(listingRepository.findByCategoryId(any(), any()))
            .thenReturn(Collections.emptyList());
        List<Listing> listingList = listingService
            .getListingByCategory(mockCategory(), testSortBy, testSortDirection);
        assertEquals(0, listingList.size());
    }

    @Test
    public void getListingByCategorySuccess() {
        when(listingRepository.findByCategoryId(any(), any()))
            .thenReturn(Collections.singletonList(mockListing()));
        List<Listing> listingList = listingService
            .getListingByCategory(mockCategory(), testSortBy, testSortDirection);
        assertEquals(1, listingList.size());
        Listing listing = listingList.get(0);
        assertNotNull(listing);
        assertEquals(testTitle, listing.getTitle());
        assertEquals(testDescription, listing.getDescription());
        assertEquals(testPrice, listing.getPrice());
    }

    @Test(expected = ListingNotFoundException.class)
    public void getTopCategoryNotFound() {
        when(listingRepository.lookUpTopCategory())
            .thenReturn(Optional.empty());
        listingService.getTopCategory();
    }

    @Test
    public void getTopCategorySuccess() {
        when(listingRepository.lookUpTopCategory())
            .thenReturn(Optional.of(testCategoryName));
        String category = listingService.getTopCategory();
        assertNotNull(category);
        assertEquals(testCategoryName, category);
    }

}
