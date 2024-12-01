package com.example.userservice.services;

import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.repositories.TokenRepository;
import com.example.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder encoder  ;
    private UserRepository UserRepository;
    private TokenRepository tokenRepository;
    public UserServiceImpl(UserRepository UserRepository,
                           TokenRepository tokenRepository ,
                           BCryptPasswordEncoder passwordEncoder) {
        this.UserRepository = UserRepository;
        this.tokenRepository = tokenRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public Token login(String email, String password) {
        return null;
    }

    @Override
    public User signUp(String name, String email, String password) {
        Optional<User> user = UserRepository.findByEmail(email);
        if(user.isPresent()) {
            return user.get();
        }
        User u = new User();
        u.setEmail(email);
        u.setPassword(encoder.encode(password));
        u.setName(name);
        u.setVerified(true);
        UserRepository.save(u);
        return u;

    }

    @Override
    public void logout(Token token) {

    }

    @Override
    public User ValidateToken(String token) {
        return null;
    }
}
