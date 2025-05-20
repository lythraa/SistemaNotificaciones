package co.edu.uniquindio.poo.sisnotificacion.app;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.AdminUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.ClientUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.EventManager;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.TipoEvento;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.EmailNotification;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.SMSNotification;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {
        EventManager manejador = new EventManager();

        NotificationStrategy canalEmail = new EmailNotification();
        NotificationStrategy canalSMS = new SMSNotification();

        User ana = new ClientUser("Ana","a@", canalEmail);
        User juan = new AdminUser("Juan","j@", canalSMS);

        manejador.suscribir(TipoEvento.PROMOCION, ana);
        manejador.suscribir(TipoEvento.ALERTA_SEGURIDAD, juan);
        manejador.suscribir(TipoEvento.PROMOCION, juan);

        manejador.notificarSuscriptores(TipoEvento.PROMOCION, "¡50% de descuento hoy!");
        manejador.notificarSuscriptores(TipoEvento.ALERTA_SEGURIDAD, "Acceso no autorizado detectado.");
    }

}
