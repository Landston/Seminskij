package com.DI.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigProperty {
    String configName() default "src/com/Models/resources/configuration.properties";
    String propertyName() default "";

    static String CONFIG_PROPERTY_FILE_PATH = "src/com/Models/resources/configuration.properties";
}
