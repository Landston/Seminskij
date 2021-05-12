package com.senla.di.appconfig;

import java.io.*;
import java.util.*;

import com.senla.di.exception.FATALERROR;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static String CONFIG_PATH = "D:\\GITLABA\\senla\\ZEnd\\src\\main\\resources\\configuration.properties";
    private static final Logger LOGGER = LogManager.getLogger(Application.class.getName());

    public static ApplicationContext run(String path, Map<Class, Class> if2impl) {


        try (InputStream input = new FileInputStream(new File(CONFIG_PATH))) {
            Map<Class, Class> map = new HashMap<>();
            Properties prop = new Properties();

            prop.load(input);

            List<Object> key = new ArrayList<>(prop.keySet());
            List<Object> values = new ArrayList<>(prop.values());

            for (int i = 0; i < key.size(); i++) {
                map.put(Class.forName(key.get(i).toString()), Class.forName(values.get(i).toString()));
            }

            JavaConfig config = new JavaConfig(path, map);
            ApplicationContext context = new ApplicationContext(config);
            ObjectFactory factory = new ObjectFactory(context);

            context.setFactory(factory);

            context.init();

            return context;

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARN, "Config file is not found", e);

            throw new FATALERROR("FATALITY", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARN, "Config file is not found", e);

            throw new FATALERROR("FATALITY", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARN, String.format("Class not found:  "), e);
            throw new FATALERROR("FATALITY", e);
        }

    }
}
