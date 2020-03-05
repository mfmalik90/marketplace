package com.carousell.marketplace.error;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
public enum ErrorType {
    UNKNOWN_USER_MESSAGE("Error - unknown user"),
    DUPLICATE_USER_MESSAGE("Error - user already existing"),
    LISTING_NOT_FOUND_MESSAGE("Error - listing does not exist"),
    LISTING_OWNER_MISMATCH_MESSAGE("Error - listing owner mismatch"),
    NOT_FOUND_MESSAGE("Error - not found"),
    CATEGORY_NOT_FOUND_MESSAGE("Error - category not found");

    private String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
