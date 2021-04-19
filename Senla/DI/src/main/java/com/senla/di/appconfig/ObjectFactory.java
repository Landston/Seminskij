package com.senla.di.appconfig;

import com.senla.di.appconfig.api.ObjectConfigurator;
import com.senla.di.exception.ConfigurationError;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;

public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private static final Logger LOGGER =  LogManager.getLogger(ObjectFactory.class.getName());


    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        try {
            Set<Class> set = new HashSet<>(context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class));

            for (Class<? extends ObjectConfigurator> aClass : set) {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            }
        } catch (NoSuchElementException | NoSuchMethodException e){

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public <T> T createObject(Class<T> type) {
        try {

            T t = type.getDeclaredConstructor().newInstance();

            configurate(t, context);

            return t;
        } catch (InstantiationException e) {
            LOGGER.log(org.apache.logging.log4j.Level.WARN ,"Exception in reflection layer");
        } catch (InvocationTargetException e) {
           // LOGGER.log(Level.WARNING, "Exception in reflection layer", e.getCause().getClass());
        } catch (NoSuchMethodException e) {
       //     LOGGER.log(Level.WARNING, "Exception in reflection layer", e.getCause().getClass());
        } catch (IllegalAccessException e) {
        }
        throw new ConfigurationError(type.toString()  );

    }

    public void configurate(Object t, ApplicationContext context) {
        configurators.forEach((o1) -> {
            System.out.println(t.getClass());
            o1.configure(t, this.context);
        });
    }
}
