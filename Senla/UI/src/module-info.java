module com.senal.ui{
    requires com.senla.model;
    requires reflections;
    requires com.senla.di;
    requires java.logging;
    opens com.senal.ui to com.senla.di;

}
