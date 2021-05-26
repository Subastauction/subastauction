package edu.eci.arsw.subastauction.controller;

import edu.eci.arsw.subastauction.model.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class STOMPMessagesHandler {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/newsubasta.{numero}")
    public void handleNewSubasta(Oferta of, @DestinationVariable String numero) {
        System.out.println("Nuevo usuario en el servidor!:" + of);
        msgt.convertAndSend("/topic/newsubasta."+numero, of);
    }

    @MessageMapping("/alloffers.{numero}")
    public void handleAllOffers(List<Oferta> of, @DestinationVariable String numero) {
        System.out.println("Nuevo usuario en el servidor!:" + of);
        msgt.convertAndSend("/topic/alloffers."+numero, of);
    }
    
}
