package com.techscode.backend.configurations;

import com.techscode.backend.configurations.beans.DatabaseCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "globalEntityManagerFactory", basePackages = {"com.techscode.backend.storage.global.repositories" })
public class GlobalDatabaseConfiguration {

    @Autowired
    private DatabaseCredentials credentials;

    @Primary
    @Bean(name = "globalDatasource")
    public DataSource dataSource() {
        return credentials.createDataSource("Main");
    }

    @Primary
    @Bean(name = "globalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("globalDatasource") DataSource globalDatasource) {
        return builder
                .dataSource(globalDatasource)
                .packages("com.techscode.backend.storage.global.entities")
                .persistenceUnit("global")
                .build();
    }

    @Primary
    @Bean(name = "globalTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("globalEntityManagerFactory") EntityManagerFactory db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory);
    }

}