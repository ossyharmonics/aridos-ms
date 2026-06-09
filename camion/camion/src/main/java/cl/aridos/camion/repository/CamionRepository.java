package cl.aridos.camion.repository;

import cl.aridos.camion.model.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CamionRepository extends JpaRepository<Camion, String> {

}
