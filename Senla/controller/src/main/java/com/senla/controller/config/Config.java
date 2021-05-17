package com.senla.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ComponentScan({"com.senla.dao", "com.senla.service", "com.senla.model"})
@EnableTransactionManagement
public class Config {


}
