package com.techscode.backend.configurations;

import com.techscode.backend.configurations.fields.PortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Autowired
    private PortField portField;

    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(portField.getPort());
        factory.setContextPath("");
    }

}