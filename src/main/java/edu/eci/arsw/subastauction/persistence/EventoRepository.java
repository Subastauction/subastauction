
package edu.eci.arsw.subastauction.persistence;

import edu.eci.arsw.subastauction.model.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String>{

}
