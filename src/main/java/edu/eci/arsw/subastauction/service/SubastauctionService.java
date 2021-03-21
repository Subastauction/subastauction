/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.subastauction.service;

import edu.eci.arsw.subastauction.model.Evento;
import edu.eci.arsw.subastauction.model.Usuario;
import edu.eci.arsw.subastauction.persistence.Database;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubastauctionService {
    
    @Autowired
    Database db;
    
    public void registrarUsuario(Usuario usuario){
        db.añadirUsuario(usuario);
    }
    
    public void crearEvento(Evento evento){
        db.añadirEvento(evento);
    }
    
    public Set<Evento> getAllEvents(){
        return db.consultarEventos();
    }
    
}
