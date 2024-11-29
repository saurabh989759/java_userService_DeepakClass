package com.example.userservice.services;

import com.example.userservice.models.Token;
import com.example.userservice.models.User;

public class UserServiceImpl implements UserService {


    @Override
    public Token login(String email, String password) {
        return null;
    }

    @Override
    public User signUp(String name, String email, String password) {
        return null;
    }

    @Override
    public void logout(Token token) {

    }

    @Override
    public User ValidateToken(String token) {
        return null;
    }
}
