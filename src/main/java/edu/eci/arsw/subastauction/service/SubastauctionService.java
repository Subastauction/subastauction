/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.subastauction.service;

import edu.eci.arsw.subastauction.cache.SubastauctionCache;
import edu.eci.arsw.subastauction.model.Evento;
import edu.eci.arsw.subastauction.model.Oferta;
import edu.eci.arsw.subastauction.model.Usuario;
import edu.eci.arsw.subastauction.persistence.EventoRepository;
import edu.eci.arsw.subastauction.persistence.OfertaRepository;
import edu.eci.arsw.subastauction.persistence.UsuarioRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicios.
 */
@Service
public class SubastauctionService {

    /**
     * Variable del cache.
     */
    @Autowired
    SubastauctionCache sbCache;

    /**
     * Repositorio con los usuarios.
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Repositorio con los eventos.
     */
    @Autowired
    EventoRepository eventoRepository;

    /**
     * Repositorio con las ofertas.
     */
    @Autowired
    OfertaRepository ofertaRepository;

    /**
     * Registra un usuario nuevo.
     * @param usuario usuario a registrar.
     */
    public void registrarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    /**
     * Agrega un evento nuevo
     * @param evento evento a agregar.
     */
    public void agregarEvento(Evento evento){
        Evento evt=eventoRepository.save(evento);
        sbCache.agregarEvento(evt);
    }

    /**
     * Consulta todos los eventos.
     * @return lista de todos los eventos.
     */
    public List<Evento> consultarEventos(){
        if(!sbCache.hayCache()){
            sbCache.agregarEventos(eventoRepository.findAll());
        }
        return sbCache.devolverCache();
    }

    /**
     * Busca un usuario por su email.
     * @param email email del usuario a buscar.
     * @return Usuario con el email dado.
     * @throws ServiceNotFoundException cuando no encuentra el usuario.
     */
    public Usuario findByEmail(String email) throws ServiceNotFoundException{
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null){
            throw new ServiceNotFoundException("Not found usuario");
        } else {
            return usuario;
        }
    }

    /**
     * Busca un evento por id.
     * @param id id del evento.
     * @return devuelve el evento con el id dado.
     * @throws ServiceNotFoundException cuando no encuentra el evento.
     */
    public Evento findEventById(String id) throws ServiceNotFoundException{
        if(sbCache.hayEvento(id)){
            return sbCache.devolverEvento(id);
        }
        else {
            Optional<Evento> evento = eventoRepository.findById(id);
            if (evento.isPresent()) {
                sbCache.agregarEvento(evento.get());
                return sbCache.devolverEvento(id);
            } else {
                throw new ServiceNotFoundException("Not found evento");
            }
        }
        
    }

    /**
     * Busca todos los eventos relacionados a un usuario.
     * @param usuario el usuario al cual relaciona los eventos.
     * @return retorna la lista de eventos asociados al usuario.
     */
    public List<Evento> findEventByUsuario(String usuario){
         return eventoRepository.findByUsuario(usuario);
    }

    /**
     * Registra una nueva oferta relacionada a un evento.
     * @param newOffer la oferta a registrar con el id del evento al cual la relaciona.
     * @throws Exception Cuando la oferta debe ser mayor.
     */
    public void registrarOferta(Oferta newOffer) throws Exception {

        List<Oferta> ofertas= ofertaRepository.findAllByIdEventoOrderByCantidadDesc(newOffer.getIdEvento());
        if(ofertas.size()==0){
            ofertaRepository.save(newOffer);
        }
        else if(ofertas.get(0).getCantidad()< newOffer.getCantidad()) {
            ofertaRepository.save(newOffer);
        }
        else{
            throw new Exception("La oferta debe ser mayor a la maxima oferta registrada.");
        }
    }

    /**
     * Obtiene las ofertas por un id del evento relacionado.
     * @param idEvento id del evento relacionado.
     * @return lista de ofertas encontradas.
     * @throws Exception cuando no cuenta con ofertas relacionadas.
     */
    public List<Oferta> getOfertasByIdEvento(String idEvento) throws Exception {
        List<Oferta> ofertas=ofertaRepository.findAllByIdEventoOrderByCantidadDesc(idEvento);
        if(ofertas.size()==0) throw new Exception("No hay ofertas.");
        return ofertas;
    }

    /**
     * Busca un usuario por su id.
     * @param id id del usuario.
     * @return el usuario con el id dado.
     * @throws ServiceNotFoundException Cuando no encuentra al usuario.
     */
    public Usuario findById(String id) throws ServiceNotFoundException {
        Optional<Usuario> usuario=usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return usuario.get();
        } else {
            throw new ServiceNotFoundException("Not found usuario");
        }
    }
}
