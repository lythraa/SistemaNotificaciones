package co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod;


import co.edu.uniquindio.poo.sisnotificacion.model.observer.Observer;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.TipoEvento;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

public abstract class User implements Observer {
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

    public abstract String formatMessage(String mensaje);

    @Override
    public void notify(TipoEvento evento, String mensaje) {
        String mensajeFormateado = evento.toString()+": " + formatMessage(mensaje);
        canal.sendNotification(mensajeFormateado);
    }

    public String getNombre() {
        return nombre;
    }
}

