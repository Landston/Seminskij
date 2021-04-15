module com.senla.model {

    exports com.senla.model;
    exports com.senla.model.models;
    exports com.senla.model.services;
    exports com.senla.model.api.dao;
    exports com.senla.model.dao;
    exports com.senla.model.utils;
    requires java.logging;
    requires com.senla.di;
    requires reflections;
    requires lombok;
    opens com.senla.model.services to com.senla.di;
    opens com.senla.model to com.senla.di;
    opens com.senla.model.api.dao to com.senla.di;
    opens com.senla.model.api.service to com.senla.di;
    opens com.senla.model.serializable to com.senla.di;




}
