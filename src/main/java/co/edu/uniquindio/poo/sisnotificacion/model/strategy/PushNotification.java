package co.edu.uniquindio.poo.sisnotificacion.model.strategy;

public class PushNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String message, String destinatario) {
        System.out.println("Enviando push a: " + destinatario + " - " + message);
    }
}
