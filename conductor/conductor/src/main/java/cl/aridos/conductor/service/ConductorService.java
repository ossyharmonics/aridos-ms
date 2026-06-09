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

    /*public Conductor actualizar(Integer id, Conductor conductor) {
        Conductor c = buscarIdConductor(id);
        if(c != null){

        }
    }*/

    public boolean eliminarConductor(Integer id) {
        Conductor c = buscarPorIdConductor(id);
        if (c!= null){
            return eliminarConductor(id);
        }
        return false;
    }
    

    /**
     * Obtiene el camion asignado al conductor consultando el microservicio CAMION.
     
    public CamionDTO obtenerCamionDelConductor(String rutPersona) {
        Conductor conductor = conductorRepository.findById(rutPersona)
                .orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado: " + rutPersona));
        if (conductor.getPatente() == null) {
            return null;
        }
        return camionClient.obtenerPorPatente(conductor.getPatente());
    }*/






        public ConductorDTO buscarConductorDTO(Integer id){
            Conductor c = buscarPorIdConductor(id);
            ConductorDTO dto = new ConductorDTO();
            dto.setIdConductor(c.getIdConductor());
            return dto;
        }
}

