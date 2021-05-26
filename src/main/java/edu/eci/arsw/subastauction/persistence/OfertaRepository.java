
package edu.eci.arsw.subastauction.persistence;

import edu.eci.arsw.subastauction.model.Oferta;
import edu.eci.arsw.subastauction.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends MongoRepository<Oferta, String>{
    public List<Oferta> findAllByIdEventoOrderByCantidadDesc(String idEvento);
}
