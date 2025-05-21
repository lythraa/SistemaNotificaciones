package co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod;

import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

//Usuario con permisos de administrador.
//Recibe notificaciones especiales.
public class AdminUser extends User {
    public AdminUser(String nombre, String email, NotificationStrategy canal) {
        super(nombre, email, canal);
    }

    @Override
    public String formatMessage(String mensaje) {
        return "[ADMIN] " + nombre +" "+ mensaje;
    }

}
