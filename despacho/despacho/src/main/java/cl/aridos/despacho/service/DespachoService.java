package cl.aridos.despacho.service;

import cl.aridos.despacho.client.CamionClient;
import cl.aridos.despacho.client.DireccionClient;
import cl.aridos.despacho.client.EmpresaClient;
import cl.aridos.despacho.client.PagoClient;
import cl.aridos.despacho.client.PersonaClient;
import cl.aridos.despacho.dto.CamionDTO;
import cl.aridos.despacho.dto.DireccionDTO;
import cl.aridos.despacho.dto.EmpresaDTO;
import cl.aridos.despacho.dto.PagoDTO;
import cl.aridos.despacho.dto.PersonaDTO;
//import cl.aridos.despacho.client.CamionClient;
//import cl.aridos.despacho.dto.CamionDTO;
import cl.aridos.despacho.model.Despacho;
import cl.aridos.despacho.repository.DespachoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespachoService {
    @Autowired
    private DespachoRepository dr;
    private CamionClient conductorClient;
    private DireccionClient direccionClient;
    private EmpresaClient empresaClient;
    private PagoClient pagoClient;
    private PersonaClient personaClient;

    public List<Despacho> listar() {
        return dr.findAll();
    }

    public Despacho buscarPorIdDespacho(Integer id){
        return dr.findById(id)
        .orElseThrow(() -> new RuntimeException("Despacho no encontrada"));
    }

    public Despacho guardarDespacho(Despacho despacho) {

        CamionDTO camionDTO = conductorClient.obtenerCamionPorPatente(despacho.getPatente());
        if (camionDTO == null){
            throw new RuntimeException("Patente no encontrada.");
        }

        DireccionDTO direccionDTO = direccionClient.obtenerDireccionPorId(despacho.getIdDespacho());
        if (direccionDTO == null){
            throw new RuntimeException("Dirección no encontrada.");
        }

        EmpresaDTO empresaDTO = empresaClient.obtenerEmpresaPorRut(despacho.getRutEmpresa());
        if (empresaDTO == null){
            throw new RuntimeException("Empresa no encontrada.");
        }

        PagoDTO pagoDTO = pagoClient.obtenerPagoPorId(despacho.getIdPago());
        if (pagoDTO == null){
            throw new RuntimeException("Pago no encontrado");
        }

        PersonaDTO personaDTO = personaClient.buscarPersonaPorRut(despacho.getRutPersona());
            if (personaDTO == null){
                throw new RuntimeException("Rut no encontrado.");
        }
            return dr.save(despacho);
    }

    public Despacho actualizarDespacho(Integer idDespacho, Despacho despacho) {
        return dr.save(despacho);
    }

    public boolean eliminarDespacho(Integer idDespacho) {
        Despacho d = buscarPorIdDespacho(idDespacho);
        if (d != null){
            return eliminarDespacho(idDespacho);
        }
        return false;
    }

    /**
     * Obtiene el camion del despacho consultando el microservicio CAMION.
     
    public CamionDTO obtenerCamionDelDespacho(Long idDespacho) {
        Despacho despacho = despachoRepository.findById(idDespacho)
                .orElseThrow(() -> new IllegalArgumentException("Despacho no encontrado: " + idDespacho));
        if (despacho.getPatente() == null) {
            return null;
        }
        return camionClient.obtenerPorPatente(despacho.getPatente());
    }*/
}
