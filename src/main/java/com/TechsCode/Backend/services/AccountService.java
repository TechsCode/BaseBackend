package com.TechsCode.Backend.services;

import com.TechsCode.Backend.entities.Account;
import com.TechsCode.Backend.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account getAccountFromToken(String token){
        return repository.findBySessionToken(token);
    }

}
