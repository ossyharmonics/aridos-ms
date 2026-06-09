package cl.aridos.direccion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.aridos.direccion.dto.DireccionDTO;
import cl.aridos.direccion.model.Comuna;
import cl.aridos.direccion.model.Direccion;
import cl.aridos.direccion.model.Region;
import cl.aridos.direccion.repository.ComunaRepository;
import cl.aridos.direccion.repository.DireccionRepository;
import cl.aridos.direccion.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {

    @Autowired
    private DireccionRepository dr;

    @Autowired
    private ComunaRepository cr;

    @Autowired
    private RegionRepository regRepo;

    public List<Direccion> listarDirecciones(){
        return dr.findAll();
    }
    public Direccion buscarPorIdDireccion(Integer id){
        return dr.findById(id)
        .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
    }
    public Direccion guardarDireccion(Direccion direccion){
        return dr.save(direccion);
    }
    public Direccion actualizarDireccion(Integer id, Direccion direccion){
        Direccion d = buscarPorIdDireccion(id);
        if(d != null) {
            d.setIdDireccion(id);
            d.setCalle(direccion.getCalle());
            d.setNumeracion(direccion.getNumeracion());
            d.setIdComuna(direccion.getIdComuna());
            return dr.save(d);
        }
        return null;
    }
    public boolean eliminarDireccion(Integer id){
        Direccion d = buscarPorIdDireccion(id);
        if(d != null) {
            return eliminarDireccion(id);
        }
        return false;
    }

//******************************METODOS REGIONES**********************************

    public List<Region> listarRegiones(){
        return regRepo.findAll();
    }
    public Region buscarPorIdRegion(Integer id){
        return regRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("La región no existe"));
    }
    public Region guardarRegion(Region region){
        return regRepo.save(region);
    }
    public Region actualizarRegion(Integer id, Region region){
        Region r = buscarPorIdRegion(id);
        if(r != null) {
            r.setIdRegion(id);
            r.setNombreRegion(region.getNombreRegion());
            return regRepo.save(r);
        }
        return null;
    }
    public boolean eliminarRegion(Integer id){
        Region r = buscarPorIdRegion(id);
        if(r != null) {
            return eliminarRegion(id);
        }
        return false;
    }

    //******************************METODOS COMUNAS**********************************
    
    public List<Comuna> listarComunas(){
        return cr.findAll();
    }
    public Comuna buscarPorIdComuna(Integer id){
        return cr.findById(id).orElse(null);
    }
    public Comuna guardarComuna(Comuna comuna){
        return cr.save(comuna);
    }
    public Comuna actualizarComuna(Integer id, Comuna comuna){
        Comuna c = buscarPorIdComuna(id);
        if(c != null) {
            c.setIdComuna(id);
            c.setNombreComuna(comuna.getNombreComuna());
            return cr.save(c);
        }
        return null;
    }
    public boolean eliminarComuna(Integer id){
        Comuna c = buscarPorIdComuna(id);
        if(c != null) {
            return eliminarComuna(id);
        }
        return false;
    }




    public DireccionDTO buscarDireccionDTO(Integer id){
        Direccion d = buscarPorIdDireccion(id);
        //return new PagoDTO(p.getIdPago()); esto lo hace la IA que también está bien
        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(d.getIdDireccion());

        return dto;
    }


}

