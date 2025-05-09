package co.edu.uniquindio.poo.sisnotificacion.controller;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.AdminUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.ClientUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.GuestUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.EventManager;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.EmailNotification;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.PushNotification;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.SMSNotification;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class MainController {

    @FXML private TextField nombreField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> tipoUsuarioCombo;
    @FXML private ComboBox<String> canalCombo;
    @FXML private ComboBox<String> eventoCombo;
    @FXML private ComboBox<String> eventoSimularCombo;
    @FXML private TextField mensajeField;
    @FXML private TextArea outputArea;

    private final EventManager gestorEventos = new EventManager();

    @FXML
    public void initialize() {
        tipoUsuarioCombo.getItems().addAll("Admin", "Cliente", "Invitado");
        canalCombo.getItems().addAll("Email", "SMS", "Push");
        eventoCombo.getItems().addAll("perfil_actualizado", "alerta_seguridad", "promocion");
        eventoSimularCombo.getItems().addAll("perfil_actualizado", "alerta_seguridad", "promocion");
    }
    @FXML
    public void registrarUsuario() {
        try {
            String nombre = nombreField.getText().trim();
            String email = emailField.getText().trim();
            String tipo = tipoUsuarioCombo.getValue();
            String canal = canalCombo.getValue();
            String evento = eventoCombo.getValue();

            // Validaciones mejoradas
            if (nombre.isEmpty() || email.isEmpty() || tipo == null || canal == null || evento == null) {
                outputArea.appendText("Error: Por favor, completa todos los campos.\n");
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                outputArea.appendText("Error: El formato del email no es válido.\n");
                return;
            }

            NotificationStrategy estrategia = switch (canal) {
                case "Email" -> new EmailNotification();
                case "SMS" -> new SMSNotification();
                case "Push" -> new PushNotification();
                default -> throw new IllegalArgumentException("Canal de notificación no válido");
            };

            User usuario = switch (tipo) {
                case "Admin" -> new AdminUser(nombre, email, estrategia);
                case "Cliente" -> new ClientUser(nombre, email, estrategia);
                case "Invitado" -> new GuestUser(nombre, email, estrategia);
                default -> throw new IllegalArgumentException("Tipo de usuario no válido");
            };

            gestorEventos.suscribir(evento, usuario);
            outputArea.appendText("✓ Usuario " + nombre + " registrado exitosamente al evento '" + evento + "'.\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    @FXML
    public void simularEvento() {
        String evento = eventoSimularCombo.getValue();
        String mensaje = mensajeField.getText();

        if (evento == null || mensaje.isEmpty()) {
            outputArea.appendText("Por favor, selecciona un evento y escribe un mensaje.\n");
            return;
        }

        outputArea.appendText("Simulando evento '" + evento + "'...\n");
        gestorEventos.notificarSuscriptores(evento, mensaje);
    }
}
