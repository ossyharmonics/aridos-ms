package cl.aridos.direccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.aridos.direccion.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer>{

}
