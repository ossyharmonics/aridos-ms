package cl.aridos.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.aridos.empresa.dto.EmpresaDTO;
import cl.aridos.empresa.model.Empresa;
import cl.aridos.empresa.model.TipoGiro;
import cl.aridos.empresa.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/aridos/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService es;

    @GetMapping
    @Operation(summary = "Listar empresas",
               description = "Lista todas las empresas")
    
    public List<Empresa> ListarEmpresas() {
        return es.listarEmpresas();
    }
    @GetMapping("/{rut}")
    @Operation(summary = "Buscar por rut.",
               description = "Busca los datos de una empresa a partir de su rut.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente encontrado"), 
                           @ApiResponse(responseCode = "404", description = "Cliente no encontrado"), 
                           @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    public ResponseEntity<Empresa> buscarPorRut(@PathVariable String rut) {
        try{
            Empresa e = es.buscarPorRut(rut);
            return ResponseEntity.ok(e);
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/")
    @Operation(summary = "Crear nueva empresa.",
               description = "Ingreso nuevo de una empresa.")
    public ResponseEntity<?> guardarEmpresa(@RequestBody Empresa empresa){
        Empresa e = es.guardarEmpresa(empresa);
        if (e!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nEmpresa creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }
    @PutMapping("/{rut}")
    @Operation(summary = "Actualizar por rut",
               description = "Actualiza una empresa a partir de su rut."
     )
    public ResponseEntity<?> actualizarEmpresa(@PathVariable String rut, @RequestBody Empresa empresa){
        Empresa e = es.actualizarEmpresa(rut, empresa);
        if (e!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nEmpresa actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al actualizar.");
        }
    }
    @DeleteMapping("/{rut}")
    @Operation(summary = "Eliminar empresa por rut.",
               description = "Elimina una empresa de la base de datos por su rut."
    )
    public ResponseEntity<?> eliminarEmpresa(@PathVariable String rut){
        boolean b = es.eliminarEmpresa(rut);
        if (b) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nEmpresa eliminada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al eliminar.");
        }
    }


/*  ****************************************************************TIPO GIRO EMPRESA***************************************************************************************** */



    @GetMapping("/giros")
    public List<TipoGiro> listarTipoGiros(){
        return es.listarTipoGiros();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TipoGiro> buscarIdTipoGiro(@PathVariable Integer id){
        try{
            TipoGiro tg = es.buscarPorId(id);
            return ResponseEntity.ok(tg);
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/giros")
    public ResponseEntity<?> guardarTipoGiro(@RequestBody TipoGiro tipo){
        TipoGiro tg = es.guardarTipoGiro(tipo);
        if (tg!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nTipo de Giro creado exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }
    @PutMapping("/giros/{id}")
    public ResponseEntity<?> actualizarTipoGiro(@PathVariable Integer id,@RequestBody TipoGiro tipo){
        TipoGiro tg = es.actualizarTipoGiro(id,tipo);
        if (tg!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nTipo de Giro actualizado correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }   
    @DeleteMapping("/giros/{id}")
    public ResponseEntity<?> eliminarTipoGiro(@PathVariable Integer idGiro){
        boolean eliminado = es.eliminarTipoGiro(idGiro);
        if(eliminado){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nTipo de Giro eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }





    @GetMapping("/dto/{rut}")
    public ResponseEntity<EmpresaDTO> buscarEmpresaDTO(@PathVariable String rut){
        try{
            return ResponseEntity.ok(es.buscarEmpresaDTO(rut));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}