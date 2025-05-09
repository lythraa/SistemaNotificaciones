package co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod;


import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

public class ClientUser extends User {
        public ClientUser(String nombre, String email, NotificationStrategy canal) {

            super(nombre, email, canal);
        }

        @Override
        protected String formatMessage(String evento, String mensaje) {
            return "Hola " + nombre + ", tienes una nueva notificación: " + mensaje;
        }
    }
