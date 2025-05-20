package co.edu.uniquindio.poo.sisnotificacion.model.chain;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;

public abstract class NotificationFilter {
    protected NotificationFilter siguiente;

    public void setSiguiente(NotificationFilter siguiente) {
        this.siguiente = siguiente;
    }

    public boolean procesar(User user, String mensaje) {
        if (evaluar(user, mensaje)) {
            return siguiente == null || siguiente.procesar(user, mensaje);
        }
        return false;
    }

    protected abstract boolean evaluar(User user, String mensaje);
}
