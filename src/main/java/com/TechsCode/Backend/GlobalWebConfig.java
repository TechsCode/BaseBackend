package com.TechsCode.Backend;

import com.TechsCode.Backend.services.AuthService;
import com.TechsCode.Backend.resolvers.AccountArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class GlobalWebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthService authService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AccountArgumentResolver(authService));
    }

}
