package com.example.userservice.services;

import com.example.userservice.exceptions.ValidTokenNotFoundException;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.repositories.TokenRepository;
import com.example.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
        Optional<User> ouser = UserRepository.findByEmail(email);
        if(ouser.isEmpty())return null ;
        String Token = null ;
        User user = ouser.get() ;
        Token token = new Token();
        if(encoder.matches(password,user.getPassword())){

            token.setUser(user);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date date30DaysFromNow = calendar.getTime();
            token.setExpiryAt(date30DaysFromNow);

//            token.setExpiryAt(new Date());

            //Token value can be any random String of 128 characters.
            token.setValue(UUID.randomUUID().toString());
        }
        return token;
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
    public void logout(String tokenValue) throws ValidTokenNotFoundException {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(tokenValue
                , false , new Date()) ;

        if(tokenOptional.isEmpty()){
            throw  new ValidTokenNotFoundException( "fuck u ") ;
        }
        Token token = tokenOptional.get();
        token.setDeleted(true);
        tokenRepository.save(token);

    }

    @Override
    public User ValidateToken(String tokenValue) throws  ValidTokenNotFoundException {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(tokenValue
                , false , new Date()) ;

        if(tokenOptional.isEmpty()){
            throw  new ValidTokenNotFoundException( "fuck u ") ;
        }
        Token token = tokenOptional.get();
        User user = token.getUser();
        return user;
    }
}
