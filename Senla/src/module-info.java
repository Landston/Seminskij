module com.model {

    exports com.senla.model;
    exports com.senla.model.models;
    exports com.senla.model.services;
    exports com.senla.model.api.dao;
    exports com.senla.model.dao;
    exports com.senla.model.utils;
    requires java.logging;
    requires com.di;
    requires reflections;
    requires lombok;
    opens com.senla.model.services;



}
