package co.edu.uniquindio.poo.sisnotificacion.model.chain;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;

//Filtro que bloquea notificaciones con mensajes vacíos o nulos.
public class EmptyMessageFilter extends NotificationFilter {

    @Override
    protected boolean evaluar(User user, String mensaje) {
        return mensaje != null && !mensaje.trim().isEmpty();
    }
}
