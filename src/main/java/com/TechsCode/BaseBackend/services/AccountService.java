package com.TechsCode.BaseBackend.services;

import com.TechsCode.BaseBackend.entities.Account;
import com.TechsCode.BaseBackend.repositories.AccountRepository;
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
