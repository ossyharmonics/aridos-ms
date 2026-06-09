package cl.aridos.persona.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.aridos.persona.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, String> {
    Optional<Persona> findByRut(String rut);
}
