package cl.aridos.despacho.repository;

import cl.aridos.despacho.model.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespachoRepository extends JpaRepository<Despacho, Integer> {

    List<Despacho> findByPatente(String patente);

    List<Despacho> findByRutPersona(String rutPersona);

    List<Despacho> findByRutEmpresa(String rutEmpresa);
}
