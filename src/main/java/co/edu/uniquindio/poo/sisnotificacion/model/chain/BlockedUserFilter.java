package co.edu.uniquindio.poo.sisnotificacion.model.chain;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;

public class BlockedUserFilter extends NotificationFilter {
    @Override
    protected boolean evaluar(User user, String mensaje) {
        return !user.isBloqueado();
    }
}
