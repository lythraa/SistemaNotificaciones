package co.edu.uniquindio.poo.sisnotificacion.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteController {

    @FXML
    private RadioButton radioEmail, radioPush, radioSMS;

    @FXML
    private CheckBox checkPromociones, checkActualizaciones, checkAlertas;

    @FXML
    private Button btnGuardar, btnLimpiar, btnCerrarSesion;

    @FXML
    private ListView<String> listaNotificaciones;

    private ToggleGroup grupoCanales;
    private final ObservableList<String> notificacionesRecibidas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        grupoCanales = new ToggleGroup();
        radioEmail.setToggleGroup(grupoCanales);
        radioPush.setToggleGroup(grupoCanales);
        radioSMS.setToggleGroup(grupoCanales);

        listaNotificaciones.setItems(notificacionesRecibidas);

        btnGuardar.setOnAction(e -> guardarPreferencias());
        btnLimpiar.setOnAction(e -> limpiarNotificaciones());
        btnCerrarSesion.setOnAction(e -> cerrarSesion());

        // Simulamos notificaciones recibidas (puedes eliminarlas)
        notificacionesRecibidas.add("🎉 Promo especial para ti");
        notificacionesRecibidas.add("🔐 Tu contraseña fue cambiada");
    }

    private void guardarPreferencias() {
        String canal = obtenerCanalSeleccionado();
        if (canal == null) {
            mostrarAlerta("Selecciona un canal preferido.");
            return;
        }

        StringBuilder tipos = new StringBuilder();
        if (checkPromociones.isSelected()) tipos.append("Promociones, ");
        if (checkActualizaciones.isSelected()) tipos.append("Actualizaciones, ");
        if (checkAlertas.isSelected()) tipos.append("Alertas de seguridad, ");

        if (tipos.length() == 0) {
            mostrarAlerta("Selecciona al menos un tipo de notificación.");
            return;
        }

        String tiposStr = tipos.substring(0, tipos.length() - 2);

        mostrarInfo("Preferencias guardadas:\nCanal: " + canal + "\nNotificaciones: " + tiposStr);
    }

    private String obtenerCanalSeleccionado() {
        if (radioEmail.isSelected()) return "Email";
        if (radioPush.isSelected()) return "Push";
        if (radioSMS.isSelected()) return "SMS";
        return null;
    }

    private void limpiarNotificaciones() {
        notificacionesRecibidas.clear();
    }

    private void cerrarSesion() {
        mostrarInfo("Sesión cerrada.");
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
