package com.example.userservice.dtos;


import com.example.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenDto {
    private String value ;
    private Date expiryAt ;
    private String email ;

    public static TokenDto from(Token token) {
        TokenDto dto = new TokenDto();
        dto.setValue(token.getValue());

        dto.setExpiryAt(token.getExpiryAt());
        dto.setEmail(token.getUser().getEmail());
        return dto;
    }
}
