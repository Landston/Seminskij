package com.senla.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.senla.dao", "com.senla.service", "com.senla.model", "com.senla.ui", "com.senla.controller", "com.senla.facade"})
public class Config {

}
