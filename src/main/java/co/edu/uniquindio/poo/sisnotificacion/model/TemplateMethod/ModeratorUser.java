package co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod;

import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

//Clase para los moderadores.
public class ModeratorUser extends User {
    public ModeratorUser(String nombre, String email, NotificationStrategy canal) {
        super(nombre, email, canal);
    }

    @Override
    public String formatMessage(String mensaje) {
        return "Moderador " + nombre + " : " + mensaje;
    }

}
