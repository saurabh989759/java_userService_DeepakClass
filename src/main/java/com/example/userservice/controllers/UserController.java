package com.example.userservice.controllers;

import com.example.userservice.dtos.*;
import com.example.userservice.exceptions.ValidTokenNotFoundException;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginRequestDto requestDto) {
        Token token = userService.login(
                requestDto.getEmail(),  requestDto.getPassword()
        );
        return TokenDto.from(token) ;
    }
    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto) {
        User user = userService.signUp(
                requestDto.getName() , requestDto.getEmail() , requestDto.getPassword()
        ) ;

        return UserDto.from(user) ;
    }
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto) {
        ResponseEntity<Void> responseEntity = null ;
        try {
            userService.logout(requestDto.getTokenValue()); ;
            responseEntity = new ResponseEntity<>(
                    HttpStatus.OK
            );
        } catch (ValidTokenNotFoundException e) {
            responseEntity = new ResponseEntity<>(
                    HttpStatus.UNAUTHORIZED
            );
        }
        return responseEntity;
    }
    @GetMapping("/validate/{Token}")
    public UserDto validateToken(@PathVariable String Token ){
       try {
           User  user = userService.ValidateToken(Token) ;
           return UserDto.from(user) ;

       } catch (ValidTokenNotFoundException e) {
           throw new RuntimeException(e);
       }
    }
}
