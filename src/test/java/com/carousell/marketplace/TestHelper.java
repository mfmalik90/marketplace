package com.carousell.marketplace;

import com.carousell.marketplace.dao.User;

import java.util.Date;

/**
 * @author faizanmalik
 * creation date 3/6/20
 */
public final class TestHelper {
    private TestHelper(){}

    private static final String testUserName = "user1";
    private static final Long testId = 1L;

    public static User mockUser() {
        return User.builder()
            .id(testId)
            .username(testUserName)
            .createdAt(new Date())
            .build();
    }
}
