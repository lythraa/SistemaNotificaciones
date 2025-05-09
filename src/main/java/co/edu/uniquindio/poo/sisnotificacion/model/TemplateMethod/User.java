package co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod;


import co.edu.uniquindio.poo.sisnotificacion.model.observer.EventListener;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

public abstract class User implements EventListener {
    protected String nombre;
    protected String email;
    protected NotificationStrategy canal;
    protected boolean bloqueado;

    public User(String nombre, String email, NotificationStrategy canal) {
        this.nombre = nombre;
        this.email = email;
        this.canal = canal;
        this.bloqueado = false;
    }

    public void setCanal(NotificationStrategy canal) {
        this.canal = canal;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    protected abstract String formatMessage(String evento, String mensaje);

    public void actualizar(String evento, String mensaje) {
        String mensajeFormateado = formatMessage(evento, mensaje);
        canal.sendNotification(mensajeFormateado, email);
    }

    public abstract String formatearMensaje(String mensaje);


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
