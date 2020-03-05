package com.carousell.marketplace.service.impl;

import com.carousell.marketplace.dao.Category;
import com.carousell.marketplace.error.ErrorType;
import com.carousell.marketplace.error.exception.CategoryNotFoundException;
import com.carousell.marketplace.repository.CategoryRepository;
import com.carousell.marketplace.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getOrCreate(String name) {
        return categoryRepository.findByName(name)
            .orElseGet(() -> categoryRepository.save(new Category(name)));
    }

    @Override
    public Category getCategory(String name) {
        return categoryRepository.findByName(name)
            .orElseThrow(() -> new CategoryNotFoundException(
                name,
                ErrorType.CATEGORY_NOT_FOUND_MESSAGE
            ));
    }
}
