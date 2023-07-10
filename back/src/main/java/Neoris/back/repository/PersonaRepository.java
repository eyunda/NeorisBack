package Neoris.back.repository;

import Neoris.back.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona save(Persona persona);
}

