package co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod;


import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

public class AdminUser extends User {
    public AdminUser(String nombre, String email, NotificationStrategy canal) {
        super(nombre, email, canal);
    }

    @Override
    protected String formatMessage(String evento, String mensaje) {
        return "[ADMIN] " + nombre + " - Evento: " + evento + " >> " + mensaje;
    }
}
