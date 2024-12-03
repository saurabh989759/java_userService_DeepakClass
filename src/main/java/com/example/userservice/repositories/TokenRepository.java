package com.example.userservice.repositories;

import com.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Override
    Token save(Token  token);



    //Declared Query
    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String value, boolean deleted, Date expiryAt);


    @Query("SELECT COUNT(t) FROM tokens t WHERE t.user.id = :userId AND t.deleted = false")
    long countByUserId(@Param("userId") Long userId);
}
