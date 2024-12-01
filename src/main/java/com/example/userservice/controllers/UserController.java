package com.example.userservice.controllers;

import com.example.userservice.dtos.*;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
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
        return null ;
    }
    @GetMapping("/validate")
    public UserDto validateToken(@PathVariable String Token ){
     return null ;
    }
}
