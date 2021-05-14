package com.senla.serializable;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.model.AbstractEntity;


import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serializer<T extends Serializable> {


    private static String path;
    private static final Logger LOGGER = Logger.getLogger(Serializer.class.getName());

    static {
        try {
            path = PropertyHanlder.getProperties("path").orElseThrow(() -> new ServiceException("Serialization failed"));
        } catch (ServiceException e) {
            LOGGER.log(Level.WARNING, "Serialization property path failed", e);
        }
        Long lg;
    }

    public Serializer() throws ServiceException {
    }

    @SafeVarargs
    public static void serialize(List<? extends AbstractEntity>... items) throws DAOException {
        List<List<? extends AbstractEntity>> marshalingList = List.of(items);

        try (ObjectOutputStream fileInputStream = new ObjectOutputStream(new FileOutputStream((new File(path))))) {
            fileInputStream.writeObject(marshalingList);

        } catch (IOException file) {
            LOGGER.log(Level.WARNING, "File for serialization is not found", file);
            throw new DAOException("SERIALIZATION_ERROR", file);

        }
    }

    public static <T> List<T> deserialize(Class<T> tClass) {
        try (ObjectInputStream fileInputStream = new ObjectInputStream(new FileInputStream((new File(path))))) {
            List<List<? extends AbstractEntity>> marslingListOfObjects = (List<List<? extends AbstractEntity>>) fileInputStream.readObject();

            for (List<? extends AbstractEntity> entityIDS : marslingListOfObjects) {
                if (!entityIDS.isEmpty() && entityIDS.get(0).getClass().equals(tClass)) {
                    return (List<T>) entityIDS;
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "File for serialization is not found", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "File for serialization is not found", e);
          //  e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
