package co.edu.uniquindio.poo.sisnotificacion.model.strategy;

//Clase para enviar notificaciones por email.
public class EmailNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String message) {
        System.out.println("Enviando email a: " + message);
    }
}
