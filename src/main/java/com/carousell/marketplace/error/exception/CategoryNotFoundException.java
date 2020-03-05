package com.carousell.marketplace.error.exception;

import com.carousell.marketplace.error.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryNotFoundException extends ApplicationException {
    String category;

    public CategoryNotFoundException(String category, ErrorType errorType) {
        super(errorType);
        this.category = category;
    }
}
