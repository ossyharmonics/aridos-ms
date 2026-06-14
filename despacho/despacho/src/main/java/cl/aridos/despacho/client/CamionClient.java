package cl.aridos.despacho.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.aridos.despacho.dto.CamionDTO;

@FeignClient(name = "camion", url= "http://localhost:8086")
public interface CamionClient {

    @GetMapping("/aridos/camiones/dto/{patente}")
    CamionDTO obtenerCamionPorPatente(@PathVariable("patente") String patente);
}
