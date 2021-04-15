module com.senla.ui {
    requires com.senla.model;
    requires reflections;
    requires com.senla.di;
    requires java.logging;
    opens com.senla.ui to com.senla.di;
    exports com.senla.ui;
    exports com.senla.ui.actions;


}
