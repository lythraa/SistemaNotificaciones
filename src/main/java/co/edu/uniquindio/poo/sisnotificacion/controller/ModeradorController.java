package co.edu.uniquindio.poo.sisnotificacion.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModeradorController {

    @FXML
    private ListView<String> listaNotificaciones;

    @FXML
    private TextField campoCorreoAdmin;

    @FXML
    private TextArea campoRazonBloqueo;

    @FXML
    private Button btnBloquearAdmin;

    private final ObservableList<String> notificaciones = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaNotificaciones.setItems(notificaciones);

        notificaciones.addAll(
                "🔔 Actualización de perfil enviada a juan@mail.com",
                "🔐 Alerta de seguridad para ana@mail.com",
                "🎉 Promoción enviada a varios usuarios"
        );

        btnBloquearAdmin.setOnAction(e -> bloquearAdmin());
    }

    private void bloquearAdmin() {
        String correoAdmin = campoCorreoAdmin.getText().trim();
        String razon = campoRazonBloqueo.getText().trim();

        if (correoAdmin.isEmpty()) {
            mostrarAlerta("Debes ingresar el correo del administrador.");
            return;
        }

        if (razon.isEmpty()) {
            mostrarAlerta("Debes ingresar una razón para el bloqueo.");
            return;
        }

        mostrarInfo("Administrador " + correoAdmin + " bloqueado.\nRazón: " + razon);

        campoCorreoAdmin.clear();
        campoRazonBloqueo.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
