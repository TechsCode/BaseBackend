package com.TechsCode.Backend.resolvers;

import com.TechsCode.Backend.auth.AuthManager;
import com.TechsCode.Backend.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccountArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private AuthManager authManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Account.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        return authManager.getLoggedAccount(request);
    }
}
