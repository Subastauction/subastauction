/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.subastauction.controller;

import edu.eci.arsw.subastauction.model.Evento;
import edu.eci.arsw.subastauction.model.Usuario;
import edu.eci.arsw.subastauction.service.SubastauctionService;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    @RequestMapping(method = RequestMethod.POST)	
    public ResponseEntity<?> AddNewUser(@RequestBody Usuario newUser){
        
        try {
            service.registrarUsuario(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        
    }
    
    @RequestMapping(value="/crearEvento", method = RequestMethod.POST)	
    public ResponseEntity<?> AddNewEvent(@RequestBody Evento newEvent){
        
        try {
            service.agregarEvento(newEvent);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        
    }
    
    @RequestMapping(value="/consultarEventos", method = RequestMethod.GET)
    public ResponseEntity<?> GetAllEvents(){
        try {
            return new ResponseEntity<>(service.consultarEventos(),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/usuario/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> GetUserByName(@PathVariable("email") String email ){
        try {
            return new ResponseEntity<>(service.findByEmail(email),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(SubastauctionAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}

