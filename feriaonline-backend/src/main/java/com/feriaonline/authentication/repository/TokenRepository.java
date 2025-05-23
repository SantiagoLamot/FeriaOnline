package com.feriaonline.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer>  {
    
}
