package cl.aridos.pago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.aridos.pago.dto.PagoDTO;
import cl.aridos.pago.model.Pago;
import cl.aridos.pago.model.TipoPago;
import cl.aridos.pago.repository.PagoRepository;
import cl.aridos.pago.repository.TipoPagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {
    @Autowired
    private PagoRepository pr;
    @Autowired
    private TipoPagoRepository tpr;

    public List<Pago> listarPagos(){
        return pr.findAll();
    }
    public Pago buscarPorIdPago(Integer id){
        return pr.findById(id).get();
    }
    public Pago guardarPago(Pago pago){
        return pr.save(pago);
    }
    public boolean eliminarPago(Integer id){
        Pago p = buscarPorIdPago(id);
        if (p!= null) {
            pr.delete(p);
            return true;
        }
        return false;
    }
    


//***************************TIPO DE PAGO***************************

    public List<TipoPago> listarTipoPagos(){
        return tpr.findAll();
    }
    public TipoPago buscarPorIdTipoPago(Integer id){
        return tpr.findById(id).get();
    }
    public TipoPago guardarTipoPago(TipoPago tipoPago){
        return tpr.save(tipoPago);
    }
    public boolean eliminarTipoPago(Integer id){
        TipoPago tp = buscarPorIdTipoPago(id);
        if (tp!= null) {
            tpr.delete(tp);
            return true;
        }
        return false;
    }
    public TipoPago actualizarTipoPago(Integer idTipoPago, TipoPago tipoPago){
        TipoPago tp = tpr.findById(idTipoPago).orElse(null);
        if (tp!= null) {
            tp.setIdTipoPago(idTipoPago);
            tp.setNombreTipoPago(tipoPago.getNombreTipoPago());
            return tpr.save(tp);
        }
        return null;
    }
    



    public PagoDTO buscarPagoDTO(Integer id){
        Pago p = buscarPorIdPago(id);
        //return new PagoDTO(p.getIdPago()); esto lo hace la IA que también está bien
        PagoDTO dto = new PagoDTO();
        dto.setIdPago(p.getIdPago());

        return dto;
    }
}
