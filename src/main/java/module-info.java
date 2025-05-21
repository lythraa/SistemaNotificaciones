module co.edu.uniquindio.poo.sisnotificacion {
    requires javafx.controls;
    requires javafx.fxml;

    opens views to javafx.fxml;
    opens co.edu.uniquindio.poo.sisnotificacion.controller to javafx.fxml;
    opens co.edu.uniquindio.poo.sisnotificacion.model to javafx.fxml;

    exports co.edu.uniquindio.poo.sisnotificacion.app;
}
