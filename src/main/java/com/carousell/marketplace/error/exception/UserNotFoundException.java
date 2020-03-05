package com.carousell.marketplace.error.exception;

import com.carousell.marketplace.error.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotFoundException extends ApplicationException{
    private String username;

    public UserNotFoundException(String username, ErrorType errorType) {
        super(errorType);
        this.username = username;
    }
}
