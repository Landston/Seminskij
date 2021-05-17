package com.senla.dao.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.senla.dao")
@PropertySource( "classpath:application.properties")
public class JpaConfiguration {


    @Value("${hibernate.show_sql:false}")
    private String hibernateShowSql;
    @Value("${hibernate.connection.driver_class}")
    private String driverClass;
    @Value("${database.url}")
    private String dataBaseUrl;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;
    @Value("${hibernate.cache.provider_class}")
    private String hibernateCacheProviderClass;
    @Value("${hibernate.enable_lazy_load_no_trans}")
    private String hibernateEnableLazyLoadNoTrans;
    @Value("${hibernate.use_jdbc_metadata_defaults}")
    private String hibernateUseJdbcMetadateDefaults;

    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource(dataBaseUrl, username, password);
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory factory){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(factory);
        return jpaTransactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
       LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();

       entityManager.setDataSource(dataSource());
       entityManager.setPackagesToScan("com.senla.model");

       JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
       entityManager.setJpaVendorAdapter(jpaVendorAdapter);
       entityManager.setJpaProperties(getJpaProperties());

       return entityManager;
    }

    public Properties getJpaProperties(){
        Properties properties = new Properties();

        properties.setProperty("hibernate.show_sql", hibernateShowSql);
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.setProperty("hibernate.cache.provider_class", hibernateCacheProviderClass);
        properties.setProperty("hibernate.enable_lazy_load_no_trans", hibernateEnableLazyLoadNoTrans);
        properties.setProperty("hibernate.use_jdbc_metadata_defaults", hibernateUseJdbcMetadateDefaults);

        return properties;
    }

}
