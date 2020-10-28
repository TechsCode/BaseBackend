package com.TechsCode.Backend.services;

import com.TechsCode.Backend.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthService {

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
