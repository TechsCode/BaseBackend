package com.TechsCode.Backend;

import com.TechsCode.Backend.config.fields.FrontendField;
import com.TechsCode.Backend.services.SessionService;
import com.TechsCode.Backend.resolvers.AccountArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;

@Configuration
public class GlobalWebConfig implements WebMvcConfigurer {

    private static File staticFolder = new File("static");

    @Autowired
    private FrontendField frontendField;

    @Autowired
    private SessionService sessionService;


    /* Adding Static File Locations from Frontend & Other Static Backend Files
     * those folders will be indexed if a request is made
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] staticLocations = new String[]{
                "file:"+ frontendField.getPath().getAbsolutePath()+"/",
                "file:"+ staticFolder+"/"
        };

        registry.addResourceHandler("/**").addResourceLocations(staticLocations);
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AccountArgumentResolver(sessionService));
    }

}
