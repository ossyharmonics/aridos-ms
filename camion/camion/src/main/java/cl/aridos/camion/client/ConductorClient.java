package cl.aridos.camion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.aridos.camion.dto.ConductorDTO;

@FeignClient(name= "conductor", url= "http://localhost:8085")
public interface ConductorClient {


    @GetMapping("/aridos/conductores/dto/{id}")
    ConductorDTO buscarConductorPorId(@PathVariable("id") Integer id);

}
