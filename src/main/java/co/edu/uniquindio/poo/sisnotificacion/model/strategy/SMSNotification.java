package co.edu.uniquindio.poo.sisnotificacion.model.strategy;

public class SMSNotification implements NotificationStrategy {
    @Override
    public void sendNotification(String message, String destinatario) {
        System.out.println("Enviando SMS a: " + destinatario + message);
    }
}
