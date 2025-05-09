package co.edu.uniquindio.poo.sisnotificacion.model.strategy;

public class EmailNotification implements NotificationStrategy {
    @Override
    public void sendNotification(String message, String destinatario) {
        System.out.println("Enviando email a: " + destinatario + message);
    }
}
