package co.edu.uniquindio.poo.sisnotificacion.model.strategy;

public class PushNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String message) {
        System.out.println("Enviando push a: " + message);
    }
}
