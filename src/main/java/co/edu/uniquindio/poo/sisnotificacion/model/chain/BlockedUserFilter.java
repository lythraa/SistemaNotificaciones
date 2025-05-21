package co.edu.uniquindio.poo.sisnotificacion.model.chain;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;

//Filtro que impide notificaciones a usuarios bloqueados.
public class BlockedUserFilter extends NotificationFilter {
    @Override
    protected boolean evaluar(User user, String mensaje) {
        return !user.isBloqueado();
    }
}
