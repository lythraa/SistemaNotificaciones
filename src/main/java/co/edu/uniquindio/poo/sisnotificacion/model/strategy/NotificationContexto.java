package co.edu.uniquindio.poo.sisnotificacion.model.strategy;

public class NotificationContexto {
    NotificationStrategy strategy;

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void sendNotification(String message, String destinatario) {
        strategy.sendNotification(message, destinatario);
    }
}
