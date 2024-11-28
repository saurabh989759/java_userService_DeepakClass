package com.example.userservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenDto {
    private String value ;
    private Date expiryAt ;
    private String email ;
}
