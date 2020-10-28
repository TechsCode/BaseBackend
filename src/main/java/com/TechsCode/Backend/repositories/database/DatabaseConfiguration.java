package com.TechsCode.Backend.repositories.database;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Configuration
public class DatabaseConfiguration {

    private DatabaseCredentials credentials;

    private DatabaseConfiguration() {
        File file = new File("config.json");

        try {
            if(!file.exists()){
                if(file.createNewFile()){
                    InputStream src = DatabaseConfiguration.class.getResourceAsStream("/database.json");
                    Files.copy(src, Paths.get(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            credentials = gson.fromJson(json, DatabaseCredentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    @Primary
    public DataSource primaryDataSource() {
        return credentials.createDataSource("Main");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.TechsCode");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true); //Auto creating scheme when true
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);//Database type
        return hibernateJpaVendorAdapter;
    }

}
