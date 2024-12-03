package com.example.userservice.services;


import com.example.userservice.exceptions.ValidTokenNotFoundException;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;

public interface UserService {
    Token login(String email, String password);
    User signUp(String name , String email, String password);
    void logout(String token) throws ValidTokenNotFoundException;
    User ValidateToken (String token) throws ValidTokenNotFoundException;
}
