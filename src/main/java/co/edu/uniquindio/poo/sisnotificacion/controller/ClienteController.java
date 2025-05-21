package co.edu.uniquindio.poo.sisnotificacion.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.sisnotificacion.model.RefrescarTodo;
import co.edu.uniquindio.poo.sisnotificacion.model.SistemaNotificaciones;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.TipoEvento;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.EmailNotification;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.PushNotification;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.SMSNotification;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ClienteController {

    private final SistemaNotificaciones sistema = SistemaNotificaciones.getInstancia();
    private User clienteActual = SistemaNotificaciones.getInstancia().getClienteActual();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkPromociones;

    @FXML
    private CheckBox checkActualizaciones;

    @FXML
    private CheckBox checkAlertas;

    @FXML
    private RadioButton radioPush;

    @FXML
    private RadioButton radioSMS;

    @FXML
    private ListView<String> listaNotificaciones;

    @FXML
    private RadioButton radioEmail;

    @FXML
    void btnGuardar() {
        // Seleccionar canal
        if (radioEmail.isSelected()) {
            clienteActual.setCanal(new EmailNotification());
        } else if (radioSMS.isSelected()) {
            clienteActual.setCanal(new SMSNotification());
        } else if (radioPush.isSelected()) {
            clienteActual.setCanal(new PushNotification());
        }

        // Actualizar suscripciones: primero quitar todas para luego añadir
        sistema.getEventManager().desuscribirTodosLosEventos(clienteActual);

        if (checkPromociones.isSelected()) {
            sistema.getEventManager().suscribir(TipoEvento.PROMOCION, clienteActual);
        }
        if (checkActualizaciones.isSelected()) {
            sistema.getEventManager().suscribir(TipoEvento.ACTUALIZACION_PERFIL, clienteActual);
        }
        if (checkAlertas.isSelected()) {
            sistema.getEventManager().suscribir(TipoEvento.ALERTA_SEGURIDAD, clienteActual);
        }

        System.out.println("[INFO] Preferencias guardadas para: " + clienteActual.getNombre());
        RefrescarTodo.refrescarTodo();
    }


    @FXML
    void btnLimpiarNotificaciones() {
        listaNotificaciones.getItems().clear();
    }


    public void refrescarVista() {
        clienteActual = sistema.getClienteActual(); // Actualizar referencia por si cambió

        if (clienteActual.getCanal() instanceof EmailNotification) {
            radioEmail.setSelected(true);
        } else if (clienteActual.getCanal() instanceof SMSNotification) {
            radioSMS.setSelected(true);
        } else if (clienteActual.getCanal() instanceof PushNotification) {
            radioPush.setSelected(true);
        } else {
            radioEmail.setSelected(false);
            radioSMS.setSelected(false);
            radioPush.setSelected(false);
        }

        checkPromociones.setSelected(sistema.getEventManager().obtenerSuscriptores(TipoEvento.PROMOCION).contains(clienteActual));
        checkActualizaciones.setSelected(sistema.getEventManager().obtenerSuscriptores(TipoEvento.ACTUALIZACION_PERFIL).contains(clienteActual));
        checkAlertas.setSelected(sistema.getEventManager().obtenerSuscriptores(TipoEvento.ALERTA_SEGURIDAD).contains(clienteActual));


        listaNotificaciones.getItems().clear();
        listaNotificaciones.getItems().addAll(clienteActual.getHistorialNotificaciones());
    }

    @FXML
    void initialize() {
        assert checkPromociones != null;
        assert checkActualizaciones != null;
        assert checkAlertas != null;
        assert radioPush != null;
        assert radioSMS != null;
        assert listaNotificaciones != null;
        assert radioEmail != null;

        ToggleGroup grupoCanal = new ToggleGroup();
        radioEmail.setToggleGroup(grupoCanal);
        radioSMS.setToggleGroup(grupoCanal);
        radioPush.setToggleGroup(grupoCanal);

        if (clienteActual.getCanal() instanceof EmailNotification) {
            radioEmail.setSelected(true);
        } else if (clienteActual.getCanal() instanceof SMSNotification) {
            radioSMS.setSelected(true);
        } else if (clienteActual.getCanal() instanceof PushNotification) {
            radioPush.setSelected(true);
        }

        if (sistema.getEventManager().obtenerSuscriptores(TipoEvento.PROMOCION).contains(clienteActual)) {
            checkPromociones.setSelected(true);
        }
        if (sistema.getEventManager().obtenerSuscriptores(TipoEvento.ACTUALIZACION_PERFIL).contains(clienteActual)) {
            checkActualizaciones.setSelected(true);
        }
        if (sistema.getEventManager().obtenerSuscriptores(TipoEvento.ALERTA_SEGURIDAD).contains(clienteActual)) {
            checkAlertas.setSelected(true);
        }

    }
}

