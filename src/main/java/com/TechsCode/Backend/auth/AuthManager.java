package com.TechsCode.Backend.auth;

import com.TechsCode.Backend.entities.Account;
import com.TechsCode.Backend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthManager {

    @Autowired
    private AccountService accountService;

    public void login(Account account, HttpServletResponse response){
        Cookie cookie = new Cookie("sessionToken", account.getSessionToken());
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("sessionToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public Account getLoggedAccount(HttpServletRequest request){
        if(request.getCookies() == null){
            return null;
        }

        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("sessionToken")){
                String token = cookie.getValue();

                return accountService.getAccountFromToken(token);
            }
        }

        return null;
    }


}
