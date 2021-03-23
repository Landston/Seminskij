package com.senla.di.annotationhandlers;

import com.senla.di.annotations.Auttowared;
import com.senla.di.applicationConfigs.ApplicationContext;
import com.senla.di.applicationConfigs.ObjectConfigurator;

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
