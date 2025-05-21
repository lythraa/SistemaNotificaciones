package co.edu.uniquindio.poo.sisnotificacion.model.command;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.TipoEvento;

public class SendNotificationCommand implements NotificationCommand {

    private final User user;
    private final TipoEvento evento;
    private final String mensaje;

    public SendNotificationCommand(User user, TipoEvento evento, String mensaje) {
        this.user = user;
        this.evento = evento;
        this.mensaje = mensaje;
    }

    @Override
    public void ejecutar() {
        user.notify(evento, mensaje);
        System.out.println("[LOG] Enviando notificación a " + user.getNombre() +
                " por evento " + evento + " con mensaje: " + mensaje);

        user.agregarNotificacionAlHistorial("Tipo: "+evento + "mensaje: " + mensaje );
    }
}
