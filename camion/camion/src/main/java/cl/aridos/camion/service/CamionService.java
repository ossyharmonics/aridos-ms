package cl.aridos.camion.service;

import cl.aridos.camion.client.ConductorClient;
import cl.aridos.camion.dto.CamionDTO;
import cl.aridos.camion.dto.ConductorDTO;
//import cl.aridos.camion.client.ConductorClient;
//import cl.aridos.camion.dto.ConductorDTO;
import cl.aridos.camion.model.Camion;
import cl.aridos.camion.repository.CamionRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CamionService {
    @Autowired
    private CamionRepository cr;

    @Autowired
    private  ConductorClient conductorClient;

    public List<Camion> listarCamiones() {
        return cr.findAll();
    }

    public List<Camion> listar() {
        return cr.findAll();
    }

    public Camion buscarPorPatenteCamion(String patente){
        return cr.findById(patente)
        .orElseThrow(()-> new RuntimeException("La patente no existe."));
    }


    public Camion guardarCamion(Camion camion){
        ConductorDTO cdto = conductorClient.buscarConductorPorId(camion.getIdConductor());
        if(cdto == null){
            throw new RuntimeException("El conductor no existe.");
        }
        return cr.save(camion);
    }

    /*public Camion actualizarCamion(Integer id, Camion  camion) {
        Camion c = buscarPorPatenteCamion(id);
        if(c != null){

        }
    }*/

    public boolean eliminarCamion(String id) {
        Camion c = buscarPorPatenteCamion(id);
        if (c!= null){
            cr.delete(c);
            return true;
        }
        return false;
    }

    /**
     * Obtiene el conductor asignado a un camion consultando el microservicio CONDUCTOR.
     
    public ConductorDTO obtenerConductorDelCamion(String patente) {
        Camion camion = camionRepository.findById(patente)
                .orElseThrow(() -> new IllegalArgumentException("Camion no encontrado: " + patente));
        if (camion.getIdConductor() == null) {
            return null;
        }
        return conductorClient.obtenerPorIdConductor(camion.getIdConductor());
    }*/
 
        
        public CamionDTO buscarCamionDTO(String patente){
        Camion c = buscarPorPatenteCamion(patente);
        //return new PagoDTO(p.getIdPago()); esto lo hace la IA que también está bien
        CamionDTO dto = new CamionDTO();
        dto.setPatente(c.getPatente());

        return dto;
    }
}      
