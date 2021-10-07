package com.example.shopapp.services;

import com.example.shopapp.models.User;
import com.example.shopapp.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final User USER = User.builder()
            .username("user")
            .build();
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        doReturn(USER)
                .when(userRepo).findByUsername("user");
    }

    @Test
    void testLoadUserByUsername() {
        var user = userService.loadUserByUsername("user");
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("admin");
        });
        verify(userRepo).findByUsername("user");
        assertEquals("user", user.getUsername());
        assertTrue(exception.getMessage().contains("admin"));
    }
}