package co.edu.uniquindio.poo.sisnotificacion.model.observer;

public interface Subject {
    void suscribir(TipoEvento tipo, Observer observer);
    void desuscribir(TipoEvento tipo, Observer observer);
    void notificarSuscriptores(TipoEvento tipo, String mensaje);
}
