
package edu.eci.arsw.subastauction.persistence;

import edu.eci.arsw.subastauction.model.Oferta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends MongoRepository<Oferta, String>{
    
}
