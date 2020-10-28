package com.TechsCode.Backend;

import com.TechsCode.Backend.repositories.database.DatabaseConfiguration;
import com.TechsCode.Backend.repositories.database.DatabaseCredentials;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Configuration
public class BackendConfiguration {

    private static final Gson gson = new Gson();

    private JsonObject content;

    public BackendConfiguration() {
        File file = new File("backend.json");

        try {
            if(!file.exists()){
                if(file.createNewFile()){
                    InputStream src = DatabaseConfiguration.class.getResourceAsStream("/backend.json");
                    Files.copy(src, Paths.get(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            content = (JsonObject) JsonParser.parseString(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public DatabaseCredentials getCredentials(){
        return gson.fromJson(content.getAsJsonObject("database"), DatabaseCredentials.class);
    }
}
