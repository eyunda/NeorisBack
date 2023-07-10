package Neoris.back.repository;

import Neoris.back.DTO.ClienteDTO;
import Neoris.back.DTO.ClienteDTOResponse;
import Neoris.back.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "" +
            "SELECT c.id, p.nombre, p.genero, p.edad, p.identificacion, p.direccion, p.telefono, c.contrasena, c.estado " +
            "FROM cliente c " +
            "JOIN persona p ON c.persona_id = p.id " +
            "", nativeQuery = true)
    List<ClienteDTOResponse> obtenerClientes();

}

