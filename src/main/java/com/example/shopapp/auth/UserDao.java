package com.example.shopapp.auth;

import java.util.Optional;

public interface UserDao {

    Optional<User> selectUserByUsername(String username);
}
