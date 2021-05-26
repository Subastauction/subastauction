package edu.eci.arsw.subastauction.cache;

import edu.eci.arsw.subastauction.model.Evento;

import java.util.List;

/**
 * Interface para implementar el cache.
 */
public interface SubastauctionCache {

    /**
     * Verifica si ya existe cache.
     * @return true si hay cache.
     */
    boolean hayCache();

    /**
     * Verifica si ya existe un evento con ese id.
     * @param id id del evento.
     * @return true si existe el evento.
     */
    boolean hayEvento(String id);

    /**
     * Devuelve la lista de todos los eventos.
     * @return la lista de todos los eventos.
     */
    List<Evento> devolverCache();

    /**
     * Devuelve el evento.
     * @param id id del evento
     * @return el evento con el id dado.
     */
    Evento devolverEvento(String id);

    /**
     * Agrega un evento al cache.
     * @param evt evento que se va a agregar.
     */
    void agregarEvento(Evento evt);

    /**
     * agrega una lista de eventos al cache
     * @param evts la lista con los eventos.
     */
    void agregarEventos(List<Evento> evts);
}
