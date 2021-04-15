module com.senla.model {

    exports com.senla.model;
    exports com.senla.model.model;
    exports com.senla.model.service;
    exports com.senla.model.api.dao;
    exports com.senla.model.dao;
    exports com.senla.model.util;
    exports com.senla.model.exception;
    requires java.logging;
    requires com.senla.di;
    requires reflections;
    requires lombok;
    opens com.senla.model.service to com.senla.di;
    opens com.senla.model to com.senla.di;
    opens com.senla.model.api.dao to com.senla.di;
    opens com.senla.model.api.service to com.senla.di;
    opens com.senla.model.serializable to com.senla.di;
    opens com.senla.model.configuration to com.senla.di;
    opens com.senla.model.facade to com.senla.di;
    exports com.senla.model.facade;
    //  requires org.postgresql.jdbc;
    requires java.sql;
    requires java.transaction.xa;




}
