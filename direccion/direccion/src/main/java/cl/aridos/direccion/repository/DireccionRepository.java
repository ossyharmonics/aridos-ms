package cl.aridos.direccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.aridos.direccion.model.Direccion;


@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer>{

}
