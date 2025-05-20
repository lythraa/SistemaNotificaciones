module co.edu.uniquindio.poo.billeteravirtual {
    requires javafx.controls;
    requires javafx.fxml;


    opens views to javafx.fxml;

    exports co.edu.uniquindio.poo.sisnotificacion.app;


}