package cl.aridos.conductor.repository;

import cl.aridos.conductor.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Integer> {

    Optional<Conductor> findByIdConductor(Integer idConductor);

}
