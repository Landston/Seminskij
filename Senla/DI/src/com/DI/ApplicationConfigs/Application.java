package com.DI.ApplicationConfigs;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static ApplicationContext run(String path, Map<Class, Class> if2impl){
        JavaConfig config = new JavaConfig(path, if2impl);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory factory = new ObjectFactory(context);
        context.setFactory(factory);
        return context;
    }
}
