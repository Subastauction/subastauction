/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.subastauction.service;

import edu.eci.arsw.subastauction.model.Evento;
import edu.eci.arsw.subastauction.model.Usuario;
import edu.eci.arsw.subastauction.persistence.EventoRepository;
import edu.eci.arsw.subastauction.persistence.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubastauctionService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    EventoRepository eventoRepository;
    
    public void registrarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
    public void agregarEvento(Evento evento){
        eventoRepository.save(evento);
    }
    
    public List<Evento> consultarEventos(){
        return eventoRepository.findAll();
    }
    
    public Usuario findByEmail(String email) throws ServiceNotFoundException{
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null){
            throw new ServiceNotFoundException("Not found usuario");
        } else {
            return usuario;
        }
    }
    
}
