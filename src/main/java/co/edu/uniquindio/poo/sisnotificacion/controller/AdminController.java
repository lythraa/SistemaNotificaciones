package co.edu.uniquindio.poo.sisnotificacion.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminController {

    @FXML
    private TextField campoCorreo;

    @FXML
    private ComboBox<String> comboTipoEvento;

    @FXML
    private TextArea areaMensaje;

    @FXML
    private Button btnAgregarCola;

    @FXML
    private ListView<String> listaColaNotificaciones;

    @FXML
    private Button btnEnviarTodas;

    private final ObservableList<String> colaNotificaciones = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        comboTipoEvento.setItems(FXCollections.observableArrayList(
                "PROMOCION", "ACTUALIZACION_PERFIL", "ALERTA_SEGURIDAD"
        ));

        listaColaNotificaciones.setItems(colaNotificaciones);

        btnAgregarCola.setOnAction(e -> agregarNotificacion());
        btnEnviarTodas.setOnAction(e -> enviarNotificaciones());
    }

    private void agregarNotificacion() {
        String correo = campoCorreo.getText();
        String tipo = comboTipoEvento.getValue();
        String mensaje = areaMensaje.getText();

        if (correo.isEmpty() || tipo == null || mensaje.isEmpty()) {
            mostrarAlerta("Por favor, completa todos los campos.");
            return;
        }

        String noti = "Para: " + correo + " | Tipo: " + tipo + " | Msg: " + mensaje;
        colaNotificaciones.add(noti);

        // Limpiar campos
        campoCorreo.clear();
        comboTipoEvento.setValue(null);
        areaMensaje.clear();
    }

    private void enviarNotificaciones() {
        if (colaNotificaciones.isEmpty()) {
            mostrarAlerta("No hay notificaciones en cola.");
            return;
        }

        // Aquí iría tu lógica real para enviar notificaciones
        for (String noti : colaNotificaciones) {
            System.out.println("ENVIANDO: " + noti);
        }

        colaNotificaciones.clear();
        mostrarInfo("¡Notificaciones enviadas con éxito!");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Información");
        info.setContentText(mensaje);
        info.showAndWait();
    }
}
