package cl.aridos.conductor.service;

import cl.aridos.conductor.dto.ConductorDTO;
import cl.aridos.conductor.model.Conductor;
import cl.aridos.conductor.repository.ConductorRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class ConductorService {
    @Autowired
    private ConductorRepository cr;

    public List<Conductor> listarConductores() {
        return cr.findAll();
    }

    public List<Conductor> listar() {
        return cr.findAll();
    }

    public Conductor buscarPorIdConductor(Integer id){
        return cr.findById(id)
            .orElseThrow(()-> new RuntimeException("El conductor no existe"));
    }


    public Conductor guardarConductor(Conductor conductor) {
        return cr.save(conductor);
    }

    public boolean eliminarConductor(Integer id) {
        Conductor c = buscarPorIdConductor(id);
        if (c!= null){
            cr.delete(c);
            return true;
        }
        return false;
    }

    public ConductorDTO buscarConductorDTO(Integer id){
        Conductor c = buscarPorIdConductor(id);
        ConductorDTO dto = new ConductorDTO();
        dto.setIdConductor(c.getIdConductor());
        return dto;
    }
}

