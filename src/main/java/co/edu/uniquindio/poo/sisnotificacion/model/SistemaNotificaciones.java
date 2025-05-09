package co.edu.uniquindio.poo.sisnotificacion.model;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationContexto;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.NotificationStrategy;

import java.util.ArrayList;
import java.util.List;

public class SistemaNotificaciones {
    private final List<User> listaUsuarios = new ArrayList<>();
    List<String> historialNotificaciones = new ArrayList<>();
    NotificationContexto contexto = new NotificationContexto();

    public void registrarUsuario(User usuario) {
        listaUsuarios.add(usuario);
    }

    public void establecerCanal(NotificationStrategy strategy) {
        contexto.setStrategy(strategy);
    }

    public void enviarNotificacion(String mensaje, Usuario destinatario) {
        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío");
        }

        if (destinatario.isBloqueado()) {
            throw new IllegalStateException("El usuario está bloqueado");
        }

        String mensajeFormateado = destinatario.formatearMensaje(mensaje);
        contexto.sendNotification(mensajeFormateado, destinatario.getNombre());

        historialNotificaciones.add("→ A " + destinatario.getNombre() + ": " + mensajeFormateado);
    }

    //Getters

    public List<User> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<String> getHistorialNotificaciones() {
        return historialNotificaciones;
    }
}
