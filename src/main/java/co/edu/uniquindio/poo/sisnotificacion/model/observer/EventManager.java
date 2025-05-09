package co.edu.uniquindio.poo.sisnotificacion.model.observer;

import java.util.*;

public class EventManager {
    private Map<String, List<EventListener>> listeners = new HashMap<>();

    public void suscribir(String evento, EventListener listener) {
        listeners.computeIfAbsent(evento, k -> new ArrayList<>()).add(listener);
    }
    public void desuscribir(String evento, EventListener listener) {
        listeners.get(evento).remove(listener);
    }

    public void notificarSuscriptores(String evento, String mensaje) {
        List<EventListener> usuarios = listeners.get(evento);
        if (usuarios != null) {
            for (EventListener l : usuarios) {
                l.actualizar(evento, mensaje);
            }
        }
    }
}
