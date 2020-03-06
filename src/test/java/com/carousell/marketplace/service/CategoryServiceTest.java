package com.carousell.marketplace.service;

import com.carousell.marketplace.dao.Category;
import com.carousell.marketplace.dao.User;
import com.carousell.marketplace.error.exception.CategoryNotFoundException;
import com.carousell.marketplace.error.exception.UserAlreadyExistException;
import com.carousell.marketplace.error.exception.UserNotFoundException;
import com.carousell.marketplace.repository.CategoryRepository;
import com.carousell.marketplace.repository.UserRepository;
import com.carousell.marketplace.service.impl.CategoryServiceImpl;
import com.carousell.marketplace.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static com.carousell.marketplace.TestHelper.mockCategory;
import static com.carousell.marketplace.TestHelper.mockUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author faizanmalik
 * creation date 3/6/20
 */
public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private static final String testCategoryName = "category1";


    @Test
    public void shouldCreateNewCategory() {
        when(categoryRepository.findByName(testCategoryName))
            .thenReturn(Optional.empty());
        when(categoryRepository.save(any()))
            .thenReturn(mockCategory());
        Category category = categoryService.getOrCreate(testCategoryName);
        assertNotNull(category);
        assertEquals(testCategoryName, category.getName());
        verify(categoryRepository, times(1))
            .findByName(testCategoryName);
        verify(categoryRepository, times(1))
            .save(any());
    }

    @Test
    public void shouldGetAlreadyExistingCategory() {
        when(categoryRepository.findByName(testCategoryName))
            .thenReturn(Optional.of(mockCategory()));
        when(categoryRepository.save(any()))
            .thenReturn(mockCategory());
        Category category = categoryService.getOrCreate(testCategoryName);
        assertNotNull(category);
        assertEquals(testCategoryName, category.getName());
        verify(categoryRepository, times(1))
            .findByName(testCategoryName);
        verify(categoryRepository, times(0))
            .save(any());
    }

    @Test(expected = CategoryNotFoundException.class)
    public void whenCategoryNotFound_thenThrowException() {
        when(categoryRepository.findByName(testCategoryName))
            .thenReturn(Optional.empty());
        categoryService.getCategory(testCategoryName);
    }

    @Test
    public void shouldGetCategory() {
        when(categoryRepository.findByName(testCategoryName))
            .thenReturn(Optional.of(mockCategory()));
        Category category = categoryService.getCategory(testCategoryName);
        assertNotNull(category);
        assertEquals(testCategoryName, category.getName());
    }

}
