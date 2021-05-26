package edu.eci.arsw.subastauction.cache;

import edu.eci.arsw.subastauction.model.Evento;

import java.util.List;

public interface SubastauctionCache {
    boolean hayCache();

    boolean hayEvento(String id);

    List<Evento> devolverCache();

    Evento devolverEvento(String id);

    void agregarEvento(Evento evt);

    void agregarEventos(List<Evento> evts);
}
