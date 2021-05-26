package edu.eci.arsw.subastauction.cache;

import edu.eci.arsw.subastauction.model.Evento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementación del cache.
 */
@Service
public class SubastauctionCacheImp implements SubastauctionCache{

    /**
     * HashMap conteniendo el cache.
     */
    private HashMap<String,Evento> eventos;

    /**
     * Constructor creando el cache.
     */
    public SubastauctionCacheImp(){
        eventos=new HashMap<>();
    }

    /**
     * Verifica si ya existe cache.
     * @return true si hay cache.
     */
    @Override
    public boolean hayCache() {
        return eventos.size()!=0;
    }

    /**
     * Verifica si ya existe un evento con ese id.
     * @param id id del evento.
     * @return true si existe el evento.
     */
    @Override
    public boolean hayEvento(String id) {
        return eventos.get(id)!=null;
    }

    /**
     * Devuelve la lista de todos los eventos.
     * @return la lista de todos los eventos.
     */
    @Override
    public List<Evento> devolverCache() {
        return new ArrayList<>(eventos.values());
    }

    /**
     * Devuelve el evento.
     * @param id id del evento
     * @return el evento con el id dado.
     */
    @Override
    public Evento devolverEvento(String id) {
        return eventos.get(id);
    }

    /**
     * Agrega un evento al cache.
     * @param evt evento que se va a agregar.
     */
    @Override
    public void agregarEvento(Evento evt) {
        eventos.put(evt.getId(),evt);
    }

    /**
     * agrega una lista de eventos al cache
     * @param evts la lista con los eventos.
     */
    @Override
    public void agregarEventos(List<Evento> evts) {
        for (Evento evt : evts) {
            eventos.put(evt.getId(), evt);
        }
    }


}
