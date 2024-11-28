package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "tokens")
public class Token extends BaseModel {
    private String value ;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false) // optional: nullable=false if user is required
    private User user;

    private Date expiryAt ;


}
