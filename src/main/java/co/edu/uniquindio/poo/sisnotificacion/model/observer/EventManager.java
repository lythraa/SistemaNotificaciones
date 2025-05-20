package co.edu.uniquindio.poo.sisnotificacion.model.observer;

import java.util.*;

public class EventManager implements Subject{
    private final Map<TipoEvento, List<Observer>> suscripciones = new HashMap<>();

    @Override
    public void suscribir(TipoEvento tipo, Observer observer) {
        suscripciones
                .computeIfAbsent(tipo, _ -> new ArrayList<>())//si no existe key de ese evento la crea
                .add(observer);
    }

    @Override
    public void desuscribir(Observer observer) {
        for (List<Observer> observers : suscripciones.values()) {
            observers.remove(observer);
        }
    }

    @Override
    public void notificarSuscriptores(TipoEvento tipo, String mensaje) {
        List<Observer> interesados = suscripciones.get(tipo);
        if (interesados != null) {
            for (Observer observer : interesados) {
                observer.notify(tipo, mensaje);
            }
        }
    }

    /**
     * Devuelve la lista de suscriptores para un tipo de evento específico.
     */
    public List<Observer> obtenerSuscriptores(TipoEvento tipo) {
        return suscripciones.getOrDefault(tipo, List.of());
    }
}
