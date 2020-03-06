package com.carousell.marketplace.service;

import com.carousell.marketplace.dao.User;
import com.carousell.marketplace.error.exception.UserAlreadyExistException;
import com.carousell.marketplace.error.exception.UserNotFoundException;
import com.carousell.marketplace.repository.UserRepository;
import com.carousell.marketplace.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static com.carousell.marketplace.TestHelper.mockUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author faizanmalik
 * creation date 3/6/20
 */
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private static final String testUserName = "user1";


    @Test(expected = UserAlreadyExistException.class)
    public void registerUserTestDuplicateUser() {
        when(userRepository.save(any()))
            .thenThrow(DataIntegrityViolationException.class);
        userService.registerUser(testUserName);
    }

    @Test
    public void registerUserTestSuccess() {
        when(userRepository.save(any()))
            .thenReturn(mockUser());
        User savedUser = userService.registerUser(testUserName);
        assertNotNull(savedUser);
        assertEquals(testUserName, savedUser.getUsername());
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserTestNotFound() {
        when(userRepository.findByUsername(testUserName))
            .thenReturn(Optional.empty());
        userService.getUser(testUserName);
    }

    @Test
    public void getUserTestSuccess() {
        when(userRepository.findByUsername(testUserName))
            .thenReturn(Optional.of(mockUser()));
        User user = userService.getUser(testUserName);
        assertNotNull(user);
        assertEquals(testUserName, user.getUsername());
    }

}
