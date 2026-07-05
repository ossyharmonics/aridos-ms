package cl.aridos.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.aridos.empresa.dto.EmpresaDTO;
import cl.aridos.empresa.model.Empresa;
import cl.aridos.empresa.model.TipoGiro;
import cl.aridos.empresa.repository.EmpresaRepository;
import cl.aridos.empresa.repository.TipoGiroRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository er;
    @Autowired
    private TipoGiroRepository tgr;


    public List<Empresa> listarEmpresas(){
        return er.findAll();
    }
    public Empresa buscarPorRut(String rut){
        return er.findByRutEmpresa(rut).get();
    }
    public Empresa guardarEmpresa(Empresa empresa){
        return er.save(empresa);
    }
    public Empresa actualizarEmpresa(String rut, Empresa empresa){
        Empresa e = er.findByRutEmpresa(rut).orElse(null);
        if (e!= null) {
            e.setRutEmpresa(rut);
            e.setNombreEmpresa(empresa.getNombreEmpresa());
            e.setIdGiro(empresa.getIdGiro());
            return er.save(e);
        }
        return null;
    }
    public boolean eliminarEmpresa(String rut){
        Empresa e = buscarPorRut(rut);
        if (e!= null) {
            er.delete(e);
            return true;
        }
        return false;
    }


//*******************************TIPO GIRO***********************************************

    public List<TipoGiro> listarTipoGiros(){
        return tgr.findAll();
    }
    public TipoGiro buscarPorId(Integer id){
        return tgr.findById(id).get();
    }
    public TipoGiro guardarTipoGiro(TipoGiro tipoGiro){
        return tgr.save(tipoGiro);
    }
    public boolean eliminarTipoGiro(Integer id){
        TipoGiro tg = buscarPorId(id);
        if (tg!= null) {
            tgr.delete(tg);
            return true;
        }
        return false;
    }
    public TipoGiro actualizarTipoGiro(Integer idGiro, TipoGiro tipoGiro){
        TipoGiro tg = tgr.findById(idGiro).orElse(null);
        if (tg!= null) {
            tg.setIdGiro(idGiro);
            tg.setNombreGiro(tipoGiro.getNombreGiro());
            return tgr.save(tg);
        }
        return null;
    }





    public EmpresaDTO buscarEmpresaDTO(String rut){
        Empresa e = buscarPorRut(rut);
        //return new PagoDTO(p.getIdPago()); esto lo hace la IA que también está bien
        EmpresaDTO dto = new EmpresaDTO();
        dto.setRutEmpresa(e.getRutEmpresa());

        return dto;
    }


}
