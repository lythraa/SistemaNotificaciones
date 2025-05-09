package co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod;


import co.edu.uniquindio.poo.sisnotificacion.model.observer.EventListener;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

public abstract class User implements EventListener {
    protected String nombre;
    protected String email;
    protected NotificationStrategy canal;

    public User(String nombre, String email, NotificationStrategy canal) {
        this.nombre = nombre;
        this.email = email;
        this.canal = canal;
    }

    public void setCanal(NotificationStrategy canal) {
        this.canal = canal;
    }

    protected abstract String formatMessage(String evento, String mensaje);

    public void actualizar(String evento, String mensaje) {
        String mensajeFormateado = formatMessage(evento, mensaje);
        canal.sendNotification(mensajeFormateado, email);
    }
}
