package cl.aridos.despacho.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.aridos.despacho.dto.PersonaDTO;

@FeignClient(name="persona", url= "http://localhost:8080")
public interface PersonaClient {
    
    @GetMapping("/aridos/personas/dto/{id}")
    PersonaDTO buscarPersonaPorRut(@PathVariable("id")String rut);

}
