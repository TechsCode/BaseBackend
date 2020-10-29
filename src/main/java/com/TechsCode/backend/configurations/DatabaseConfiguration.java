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
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "globalEntityManagerFactory",
        transactionManagerRef = "globalTransactionManager",
        basePackages = { "com.techscode.backend.entities" }
)
public class DatabaseConfiguration {

    @Autowired
    private DatabaseCredentials databaseCredentials;

    @Bean(name = "globalDataSource")
    public DataSource dataSource() {
        return databaseCredentials.createDataSource("Main");
    }

    @Bean(name = "globalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("globalDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.techscode.pluginpage.entities")
                .persistenceUnit("global")
                .build();
    }

    @Bean(name = "globalTransactionManager")
    public PlatformTransactionManager barTransactionManager(@Qualifier("globalEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

}

/*

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private DatabaseCredentials credentials;

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
        lef.setPackagesToScan("com.techscode");
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
*/