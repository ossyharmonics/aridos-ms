package cl.aridos.direccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.aridos.direccion.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {



}
