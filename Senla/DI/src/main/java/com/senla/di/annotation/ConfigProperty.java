package com.senla.di.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigProperty {
    String configName() default "D:\\Senla\\proj\\senla\\server\\src\\main\\java\\com\\senla\\model\\resource\\configProperty.properties";
    String propertyName() default "";


}
