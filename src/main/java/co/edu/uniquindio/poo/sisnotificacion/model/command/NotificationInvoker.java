package co.edu.uniquindio.poo.sisnotificacion.model.command;

import java.util.LinkedList;

public class NotificationInvoker {
    private final LinkedList<NotificationCommand> colaComandos  = new LinkedList<>();

    public void agregarComando(NotificationCommand comando) {
        colaComandos.add(comando);
    }

    public void ejecutarTodos() {
        while (!colaComandos.isEmpty()) {
            NotificationCommand comando = colaComandos.poll();// obtiene y elimina el siguiente comndo a ejecutar (o null si no hay ninguno)
            if (colaComandos.isEmpty()) {
                comando.ejecutar();
            }
        }
    }
}
