package com.techscode.backend.services;

import com.techscode.backend.storage.entities.Account;
import com.techscode.backend.exceptions.EmailTakenException;
import com.techscode.backend.exceptions.UsernameTakenException;
import com.techscode.backend.storage.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    @Autowired
    private AccountRepository repository;

    public Account getAccountFromToken(String token){
        return repository.findBySessionToken(token);
    }

    public Account getAccountByEmail(String email){
        return repository.findByEmail(email);
    }

    public Account getAccountByUsername(String username){
        return repository.findByUsername(username);
    }

    public Account createNewAccount(String email, String username, String password) throws EmailTakenException, UsernameTakenException {
        if(getAccountByEmail(email) != null){
            throw new EmailTakenException();
        }

        if(getAccountByUsername(username) != null){
            throw new UsernameTakenException();
        }

        String encryptedPassword = passwordEncoder.encode(password);

        Account account = new Account();
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(encryptedPassword);

        return repository.save(account);
    }

}
