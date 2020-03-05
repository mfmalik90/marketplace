package com.carousell.marketplace.service;

import com.carousell.marketplace.dao.User;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
public interface UserService {
    User register(String username);
    User getUser(String username);
}
