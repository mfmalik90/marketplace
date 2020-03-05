package com.carousell.marketplace.service;

import com.carousell.marketplace.dao.Category;
import com.carousell.marketplace.dao.Listing;

import java.util.List;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
public interface CategoryService {

    Category getOrCreate(String name);
    Category getCategory(String name);
}
