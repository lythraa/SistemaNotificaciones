package co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod;


import co.edu.uniquindio.poo.sisnotificacion.model.observer.Observer;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.TipoEvento;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

import java.util.ArrayList;
import java.util.List;

//Clase abstracta para los usuarios.
//Define la estructura del algoritmo de notificación.
public abstract class User implements Observer {
    protected String nombre;
    protected String email;
    protected NotificationStrategy canal;
    protected boolean bloqueado;
    private final List<String> historialNotificaciones = new ArrayList<>();

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

    public abstract String formatMessage(String mensaje);

    @Override
    public void notify(TipoEvento evento, String mensaje) {
        String mensajeFormateado = evento.toString()+": " + formatMessage(mensaje);
        canal.sendNotification(mensajeFormateado);
    }

    public void agregarNotificacionAlHistorial(String notificacion) {
        historialNotificaciones.add(notificacion);
    }

    public List<String> getHistorialNotificaciones() {
        return historialNotificaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NotificationStrategy getCanal() {
        return canal;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}

