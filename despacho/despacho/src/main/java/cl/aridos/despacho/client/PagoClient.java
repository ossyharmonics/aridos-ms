package cl.aridos.despacho.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.aridos.despacho.dto.PagoDTO;

@FeignClient(name = "pago", url= "http://localhost:8081")
public interface PagoClient {

    @GetMapping("/aridos/pagos/dto/{id}")
    PagoDTO obtenerPagoPorId(@PathVariable("id") Integer id);


}
