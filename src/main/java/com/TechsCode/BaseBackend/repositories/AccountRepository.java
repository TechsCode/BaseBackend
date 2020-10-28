package com.TechsCode.BaseBackend.repositories;

import com.TechsCode.BaseBackend.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByEmail(String email);

    Account findByUsername(String username);

    Account findBySessionToken(String token);

    @Override
    void delete(Account user);

}