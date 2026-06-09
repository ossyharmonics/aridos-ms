package cl.aridos.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.aridos.pago.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

}
