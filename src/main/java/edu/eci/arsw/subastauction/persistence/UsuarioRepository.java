/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.subastauction.persistence;

import edu.eci.arsw.subastauction.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

    /**
     * Busca el usuario por su email.
     * @param email email del usuario.
     * @return retorna el Usuario con ese email.
     */
    Usuario findByEmail(String email);
    
}
