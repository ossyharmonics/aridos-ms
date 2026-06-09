package cl.aridos.despacho.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.aridos.despacho.dto.DireccionDTO;

@FeignClient(name = "direccion", url = "http://localhost:8083")
public interface DireccionClient {

    @GetMapping("aridos/direcciones/dto/{id}")
    DireccionDTO obtenerDireccionPorId(@PathVariable("id") Integer id);
    
}
