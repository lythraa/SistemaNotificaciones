package co.edu.uniquindio.poo.sisnotificacion.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.sisnotificacion.model.SistemaNotificaciones;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.AdminUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.ModeratorUser;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ModeradorController {

    private final SistemaNotificaciones sistema = SistemaNotificaciones.getInstancia();
    private ModeratorUser moderatorUser = sistema.getModeradorActual();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField campoCorreoAdminABloquear;

    @FXML
    private ListView<String> listaNotificacionesTotalesConCorreoDeAdministrador;

    @FXML
    private TextArea campoRazonBloqueo;

    @FXML
    void btnBloquearAdmin() {
        String correo = campoCorreoAdminABloquear.getText().trim();
        String razon = campoRazonBloqueo.getText().trim();

        if (correo.isEmpty() || razon.isEmpty()) {
            System.out.println("[ERROR] Debes ingresar el correo y la razón del bloqueo.");
            return;
        }

        boolean bloqueado = sistema.bloquearAdminPorCorreo(correo);

        if (bloqueado) {
            System.out.println("[MODERADOR] Se bloqueó al administrador con correo: " + correo);
            System.out.println("[RAZÓN] " + razon);
        } else {
            System.out.println("[ERROR] No se encontró un administrador con ese correo.");
        }
        campoCorreoAdminABloquear.clear();
        campoRazonBloqueo.clear();
    }


    @FXML
    void initialize() {
        assert campoCorreoAdminABloquear != null;
        assert listaNotificacionesTotalesConCorreoDeAdministrador != null;
        assert campoRazonBloqueo != null;

        cargarMensajesDeAdmins();
    }

    private void cargarMensajesDeAdmins() {
        listaNotificacionesTotalesConCorreoDeAdministrador.getItems().clear();

        sistema.getHistorialMensajesAdmins().forEach((correo, mensajes) -> {
            listaNotificacionesTotalesConCorreoDeAdministrador.getItems().add(
                    "▶ ADMIN: " + correo + " (" + mensajes.size() + " mensajes)");

            for (String mensaje : mensajes) {
                listaNotificacionesTotalesConCorreoDeAdministrador.getItems().add("   - " + mensaje);
            }
        });
    }

    public void refrescarVista() {
        cargarMensajesDeAdmins();
    }
}
