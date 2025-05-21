package co.edu.uniquindio.poo.sisnotificacion.model.command;

import java.util.LinkedList;

//Clase que invoca el patrón command.
//Encapsula y ejecuta comandos de notificación en orden.
public class NotificationInvoker {
    private final LinkedList<NotificationCommand> colaComandos  = new LinkedList<>();

    //Agrega un comando a la lista de comandos.
    public void agregarComando(NotificationCommand comando) {
        colaComandos.add(comando);
    }

    //Ejecuta todos los comandos de la lista de comandos.
    public void ejecutarTodos() {
        while (!colaComandos.isEmpty()) {
            NotificationCommand comando = colaComandos.poll(); // obtiene y elimina el siguiente comndo a ejecutar (o null si no hay ninguno)r
            comando.ejecutar();
        }
    }
}
