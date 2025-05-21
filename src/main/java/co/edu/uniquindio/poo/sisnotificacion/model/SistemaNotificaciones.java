package co.edu.uniquindio.poo.sisnotificacion.model;

import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.AdminUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.ClientUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.ModeratorUser;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaNotificaciones {

    private static SistemaNotificaciones instancia;
    private final EventManager eventManager;
    private final NotificationInvoker invoker;
    private List<User> listaUsuarios;
    private final Map<String, List<String>> historialMensajesAdmins = new HashMap<>();
    private ClientUser clienteActual;
    private AdminUser adminActual;
    private ModeratorUser moderadorActual;

    private SistemaNotificaciones() {
        this.eventManager = new EventManager();
        this.invoker = new NotificationInvoker();
        this.listaUsuarios= new ArrayList<>();
    }

    /**
     * Obtiene la instancia singleton del sistema de notificaciones.
     *
     * @return Instancia única de SistemaNotificaciones.
     */
    public static SistemaNotificaciones getInstancia() {
        if (instancia == null) {
            instancia = new SistemaNotificaciones();
        }
        return instancia;
    }

    /**
     * Suscribe un usuario a un tipo de evento.
     *
     * @param tipo Tipo de evento al que se suscribirá el usuario.
     * @param usuario Usuario observador que se suscribirá al evento.
     */
    public void suscribirUsuario(TipoEvento tipo, Observer usuario) {
        eventManager.suscribir(tipo, usuario);
    }

    /**
     * Registra un mensaje enviado por un administrador en el historial.
     *
     * @param correoAdmin Correo del administrador.
     * @param mensaje Mensaje enviado por el administrador.
     */
    public void registrarMensajeDeAdmin(String correoAdmin, String mensaje) {
        historialMensajesAdmins
                .computeIfAbsent(correoAdmin, k -> new ArrayList<>())
                .add(mensaje);
    }

    /**
     * Recibe una notificación (evento + mensaje), la valida con la cadena de filtros y
     * la registra para su envío si es válida.
     *
     * @param tipo Tipo de evento de la notificación.
     * @param mensaje Mensaje a enviar.
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

                    // Registrar mensaje en historial si el emisor es un ADMIN
                    if (user instanceof AdminUser) {
                        registrarMensajeDeAdmin(user.getEmail(), mensaje);
                    }
                }
            }
        }
    }

    /**
     * Ejecuta todas las notificaciones pendientes agregadas al invocador.
     */
    public void enviarNotificacionesPendientes() {
        invoker.ejecutarTodos();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario Usuario a registrar.
     */
    public void registrarUsuario(User usuario) {
        listaUsuarios.add(usuario);
    }

    /**
     * Busca un usuario registrado por su correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public User buscarUsuarioPorEmail(String email) {
        for (User user : listaUsuarios) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Bloquea a un administrador dado su correo electrónico.
     *
     * @param correo Correo del administrador a bloquear.
     * @return true si fue bloqueado exitosamente, false si no se encontró o no es admin.
     */
    public boolean bloquearAdminPorCorreo(String correo) {
        for (User user : listaUsuarios) {
            if (user.getEmail().equalsIgnoreCase(correo) && user instanceof AdminUser admin) {
                admin.setBloqueado(true);
                return true;
            }
        }
        return false;
    }

    public void setClienteActual(ClientUser clienteActual) {
        this.clienteActual = clienteActual;
    }

    public ClientUser getClienteActual() {
        return clienteActual;
    }

    public List<User> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public NotificationInvoker getInvoker() {
        return invoker;
    }

    public Map<String, List<String>> getHistorialMensajesAdmins() {
        return historialMensajesAdmins;
    }

    public AdminUser getAdminActual() {
        return adminActual;
    }

    public ModeratorUser getModeradorActual() {
        return moderadorActual;
    }

    public void setAdminActual(AdminUser adminActual) {
        this.adminActual = adminActual;
    }

    public void setModeradorActual(ModeratorUser moderadorActual) {
        this.moderadorActual = moderadorActual;
    }
}
