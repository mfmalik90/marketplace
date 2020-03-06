package com.carousell.marketplace.service.impl;

import com.carousell.marketplace.dao.User;
import com.carousell.marketplace.error.exception.UserAlreadyExistException;
import com.carousell.marketplace.error.exception.UserNotFoundException;
import com.carousell.marketplace.repository.UserRepository;
import com.carousell.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.carousell.marketplace.error.ErrorType.DUPLICATE_USER_MESSAGE;
import static com.carousell.marketplace.error.ErrorType.UNKNOWN_USER_MESSAGE;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(String username) {
        User user = new User(username);
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistException(
                username,
                DUPLICATE_USER_MESSAGE
            );
        }
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException(
                username,
                UNKNOWN_USER_MESSAGE
            ));
    }
}
