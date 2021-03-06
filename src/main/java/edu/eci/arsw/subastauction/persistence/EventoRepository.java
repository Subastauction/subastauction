
package edu.eci.arsw.subastauction.persistence;

import edu.eci.arsw.subastauction.model.Evento;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String>{

    /**
     * Busca los eventos por el usuario relacionado.
     * @param usuario el usuario relacionado
     * @return devuelve la lista de eventos relacionados al usuario.
     */
    List<Evento> findByUsuario(String usuario);
    
}
