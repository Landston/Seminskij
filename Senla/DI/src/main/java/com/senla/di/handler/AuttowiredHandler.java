package com.senla.di.handler;

import com.senla.di.annotation.Auttowared;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.di.appconfig.api.ObjectConfigurator;

import java.lang.reflect.Field;

public class AuttowiredHandler implements ObjectConfigurator {

    @Override
    public void configure(Object t, ApplicationContext context) {
        try{
            Class<?> impl = t.getClass();
            for (Field declaredField : impl.getDeclaredFields()) {
                if(declaredField.isAnnotationPresent(Auttowared.class)){
                    Auttowared auttowared = declaredField.getAnnotation(Auttowared.class);

                    if(auttowared != null){
                        declaredField.setAccessible(true);

                        Object item = context.getObject(declaredField.getType());

                        declaredField.set(t, item);
                    }
                }
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
