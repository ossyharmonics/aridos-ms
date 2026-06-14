package cl.aridos.despacho.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.aridos.despacho.dto.EmpresaDTO;

@FeignClient(name="empresa", url= "http://localhost:8082")
public interface EmpresaClient {
    @GetMapping("/aridos/empresas/dto/{rut}")
    EmpresaDTO obtenerEmpresaPorRut(@PathVariable("rut") String rut);
}
