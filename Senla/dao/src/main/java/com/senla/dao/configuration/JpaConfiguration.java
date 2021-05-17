package com.senla.dao.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@PropertySource( "classpath:application.properties")
public class JpaConfiguration {

    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${database.url}")
    private String dataBaseUrl;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl}")
    private String hbm2ddl;

    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource(dataBaseUrl, username, password);
    }

    public E

}
