package co.edu.uniquindio.poo.sisnotificacion.model.observer;

//Interfaz del patrón observer.
//Define el método que deben implementar los objetos observadores.
public interface Observer {
     void notify(TipoEvento evento, String mensaje);
}
