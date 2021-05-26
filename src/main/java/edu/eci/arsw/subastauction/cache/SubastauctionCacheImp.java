package edu.eci.arsw.subastauction.cache;

import edu.eci.arsw.subastauction.model.Evento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SubastauctionCacheImp implements SubastauctionCache{

    private HashMap<String,Evento> eventos;

    public SubastauctionCacheImp(){
        eventos=new HashMap<>();
    }

    @Override
    public boolean hayCache() {
        return eventos.size()!=0;
    }

    @Override
    public boolean hayEvento(String id) {
        return eventos.get(id)!=null;
    }

    @Override
    public List<Evento> devolverCache() {
        return new ArrayList<>(eventos.values());
    }

    @Override
    public Evento devolverEvento(String id) {
        return eventos.get(id);
    }

    @Override
    public void agregarEvento(Evento evt) {
        eventos.put(evt.getId(),evt);
    }

    @Override
    public void agregarEventos(List<Evento> evts) {
        for (Evento evt : evts) {
            eventos.put(evt.getId(), evt);
        }
    }


}
