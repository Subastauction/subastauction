/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.subastauction.controller;

import edu.eci.arsw.subastauction.model.Evento;
import edu.eci.arsw.subastauction.model.Oferta;
import edu.eci.arsw.subastauction.model.Usuario;
import edu.eci.arsw.subastauction.service.SubastauctionService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subastauction")
public class SubastauctionAPIController {

    @Autowired
    SubastauctionService service;
    
    @RequestMapping("/hello")
    public String hello() {
        return "Hello from Rest";
    }
    
    @RequestMapping(value="/registrar/usuario", method = RequestMethod.POST)	
    public ResponseEntity<?> addNewUser(@RequestBody Usuario newUser){
        
        try {
            service.registrarUsuario(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        
    }
    
    @RequestMapping(value="/crear/evento", method = RequestMethod.POST)	
    public ResponseEntity<?> addNewEvent(@RequestBody Evento newEvent){
        
        try {
            service.agregarEvento(newEvent);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        
    }
    
    @RequestMapping(value="/consultar/eventos", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEvents(){
        try {
            return new ResponseEntity<>(service.consultarEventos(),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/consultar/eventos/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEventById(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(service.findEventById(id),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/usuario/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByName(@PathVariable("email") String email ){
        try {
            return new ResponseEntity<>(service.findByEmail(email),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/registrar/oferta", method = RequestMethod.POST)	
    public ResponseEntity<?> addNewOffer(@RequestBody Oferta newOffer){
        
        try {
            service.registrarOferta(newOffer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        
    }

}

