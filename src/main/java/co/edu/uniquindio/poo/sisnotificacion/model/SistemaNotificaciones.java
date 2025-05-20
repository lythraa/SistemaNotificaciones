package co.edu.uniquindio.poo.sisnotificacion.model;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;
import co.edu.uniquindio.poo.sisnotificacion.model.chain.BlockedUserFilter;
import co.edu.uniquindio.poo.sisnotificacion.model.chain.EmptyMessageFilter;
import co.edu.uniquindio.poo.sisnotificacion.model.chain.NotificationFilter;
import co.edu.uniquindio.poo.sisnotificacion.model.command.NotificationCommand;
import co.edu.uniquindio.poo.sisnotificacion.model.command.NotificationInvoker;
import co.edu.uniquindio.poo.sisnotificacion.model.command.SendNotificationCommand;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.EventManager;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.Observer;
import co.edu.uniquindio.poo.sisnotificacion.model.observer.TipoEvento;
import java.util.ArrayList;
import java.util.List;

public class SistemaNotificaciones {

    private final EventManager eventManager;
    private final NotificationInvoker invoker;
    private final List<User> listaUsuarios;

    public SistemaNotificaciones() {
        this.eventManager = new EventManager();
        this.invoker = new NotificationInvoker();
        this.listaUsuarios= new ArrayList<>();
    }

    /**
     * Suscribe un usuario a un tipo de evento.
     */
    public void suscribirUsuario(TipoEvento tipo, Observer usuario) {
        eventManager.suscribir(tipo, usuario);
    }

    /**
     * Recibe una notificación (evento + mensaje) y prepara comandos si pasan la validación.
     */
    public void recibirEventoNotificacion(TipoEvento tipo, String mensaje) {
        List<Observer> interesados = eventManager.obtenerSuscriptores(tipo);

        for (Observer observer : interesados) {
            if (observer instanceof User user) {

                // Aplicar cadena de validaciones
                NotificationFilter filtro = new EmptyMessageFilter();
                filtro.setSiguiente(new BlockedUserFilter());

                if (filtro.procesar(user, mensaje)) {
                    // Crear comando si pasa validación
                    NotificationCommand comando = new SendNotificationCommand(user, tipo, mensaje);
                    invoker.agregarComando(comando);

                    System.out.println("[LOG] Se agregó notificación para " + user.getClass().getSimpleName()
                            + " '" + user.getNombre() + "' | Evento: " + tipo + " | Mensaje: " + mensaje);
                }
            }
        }
    }

    /**
     * Ejecuta todas las notificaciones pendientes.
     */
    public void enviarNotificacionesPendientes() {
        invoker.ejecutarTodos();
    }

    public void registrarUsuario(User usuario) {
        listaUsuarios.add(usuario);
    }

    public List<User> getListaUsuarios() {
        return listaUsuarios;
    }

}
