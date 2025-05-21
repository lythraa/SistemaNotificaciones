package co.edu.uniquindio.poo.sisnotificacion.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.sisnotificacion.model.RefrescarTodo;
import co.edu.uniquindio.poo.sisnotificacion.model.SistemaNotificaciones;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.AdminUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;
import co.edu.uniquindio.poo.sisnotificacion.model.command.NotificationInvoker;
import co.edu.uniquindio.poo.sisnotificacion.model.command.SendNotificationCommand;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.TipoEvento;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminController {

    private final SistemaNotificaciones sistema = SistemaNotificaciones.getInstancia();
    private final NotificationInvoker invoker = sistema.getInvoker();
    private AdminUser adminActual = sistema.getAdminActual();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<TipoEvento> comboTipoEvento;

    @FXML
    private TextArea campoMensaje;

    @FXML
    private TextField campoCorreo;

    @FXML
    private ListView<String> listaColaNotificaciones;

    @FXML
    void btnAgregarCola() {
        String correo = campoCorreo.getText();
        TipoEvento tipo = comboTipoEvento.getValue();
        String mensaje = campoMensaje.getText();

        User user = sistema.buscarUsuarioPorEmail(correo);
        if (user == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        sistema.recibirEventoNotificacion(tipo, mensaje);
        sistema.registrarMensajeDeAdmin(adminActual.getEmail(), mensaje);
        listaColaNotificaciones.getItems().add("→ " + user.getNombre() + " | " + tipo + ": " + mensaje);

        campoCorreo.clear();
        campoMensaje.clear();
        comboTipoEvento.getSelectionModel().clearSelection();
    }


    @FXML
    void btnEnviarTodasLasNotificaciones() {
        if(!adminActual.isBloqueado()) {
            sistema.getInvoker().ejecutarTodos();
            listaColaNotificaciones.getItems().clear();
            RefrescarTodo.refrescarTodo();
        }
        else {
            System.out.println("Admin bloqueado no se mandaran las notificaciones");
        }
    }

    @FXML
    void initialize() {
        comboTipoEvento.getItems().setAll(TipoEvento.values());
        assert comboTipoEvento != null : "fx:id=\"comboTipoEvento\" was not injected: check your FXML file 'AdminView.fxml'.";
        assert campoMensaje != null : "fx:id=\"campoMensaje\" was not injected: check your FXML file 'AdminView.fxml'.";
        assert campoCorreo != null : "fx:id=\"campoCorreo\" was not injected: check your FXML file 'AdminView.fxml'.";
        assert listaColaNotificaciones != null : "fx:id=\"listaColaNotificaciones\" was not injected: check your FXML file 'AdminView.fxml'.";

    }
}
