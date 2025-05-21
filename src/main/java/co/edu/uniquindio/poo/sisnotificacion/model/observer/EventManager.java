package co.edu.uniquindio.poo.sisnotificacion.model.observer;

import java.util.*;

//Clase que gestiona los eventos y suscripciones.
public class EventManager implements Subject{
    private final Map<TipoEvento, List<Observer>> suscripciones = new HashMap<>();

    //Permite a un usuario suscribirse a un evento.
    @Override
    public void suscribir(TipoEvento tipo, Observer observer) {
        suscripciones
                .computeIfAbsent(tipo, _ -> new ArrayList<>())//si no existe key de ese evento la crea
                .add(observer);
    }

    //Permite a un usuario desuscribirse de un evento.
    @Override
    public void desuscribir(TipoEvento tipo, Observer observer) {
        List<Observer> observers = suscripciones.get(tipo);
        if (observers != null) {
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

    public void desuscribirTodosLosEventos(Observer observer) {
        for (List<Observer> observers : suscripciones.values()) {
            observers.remove(observer);
        }
    }
    /**
     * Devuelve la lista de suscriptores para un tipo de evento específico.
     */
    public List<Observer> obtenerSuscriptores(TipoEvento tipo) {
        return suscripciones.getOrDefault(tipo, List.of());
    }
}
