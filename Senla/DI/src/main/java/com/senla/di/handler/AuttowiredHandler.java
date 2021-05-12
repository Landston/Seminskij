package com.senla.di.handler;

import com.senla.di.annotation.Auttowared;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.di.appconfig.api.ObjectConfigurator;
import com.senla.di.exception.ConfigurationError;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;


public class AuttowiredHandler implements ObjectConfigurator {

    private static final Logger LOGGER = LogManager.getLogger(AuttowiredHandler.class.getName());

    @Override
    public void configure(Object t, ApplicationContext context) {
        try {
            Class<?> impl = t.getClass();

            if (impl.getSuperclass() != null) {
                configFieldsOfClass(impl.getSuperclass().getDeclaredFields(), context, t);
            }

            configFieldsOfClass(impl.getDeclaredFields(), context, t);

        } catch (Exception e) {
            LOGGER.log(Level.WARN, String.format("AutowiredHandler return exception in object %s", t.getClass()), e);
            throw new ConfigurationError();
        }
    }

    private void configFieldsOfClass(Field[] fields, ApplicationContext context, Object t) {
        try {
            for (Field declaredField : fields) {
                if (declaredField.isAnnotationPresent(Auttowared.class)) {
                    Auttowared auttowared = declaredField.getAnnotation(Auttowared.class);

                    if (auttowared != null) {
                        declaredField.setAccessible(true);

                        if (declaredField.getType().getSuperclass() != null) {

                        }

                        Object item = context.getObject(declaredField.getType());

                        declaredField.set(t, item);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARN, String.format("AutowiredHandler return exception in object %s", t.getClass()), e);
            throw new ConfigurationError();
        }

    }
}

