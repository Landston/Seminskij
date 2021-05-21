package com.senla.start;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

public class Start {
    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(Start.class);

        logger.log(Level.INFO, "MEssage from start");
        System.out.println();
    }
}
