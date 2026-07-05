package cl.aridos.despacho.controller;

//import cl.aridos.despacho.dto.CamionDTO;
import cl.aridos.despacho.model.Despacho;
import cl.aridos.despacho.service.DespachoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import java.util.List;

@RestController
@RequestMapping("/aridos/despachos")
public class DespachoController {
    @Autowired
    private DespachoService ds;

    @GetMapping
    @Operation(summary = "Listar despachos", description = "Lista todos los despachos registrados")
    public List<Despacho> listar() {
        return ds.listar();
    }

    @GetMapping("/{idDespacho}")
    @Operation(summary = "Buscar despacho por ID", description = "Busca un despacho por su identificador unico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Despacho encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Despacho no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Despacho> buscarPorIdDespacho(@PathVariable Integer idDespacho) {
        try{
            Despacho s = ds.buscarPorIdDespacho(idDespacho);
            return ResponseEntity.ok(s);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }       
    }

    @PostMapping
    @Operation(summary = "Crear despacho", description = "Crea un nuevo despacho en el sistema")
    public ResponseEntity<?> crearDespacho(@RequestBody Despacho despacho){
        Despacho d = ds.guardarDespacho(despacho);
        if (d != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nDespacho creado exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }

    @PutMapping("/{idDespacho}")
    @Operation(summary = "Actualizar despacho", description = "Actualiza los datos de un despacho existente")
    public ResponseEntity<?> actualizarDespacho(@PathVariable Integer idDespacho, @RequestBody Despacho despacho){
        Despacho d = ds.actualizarDespacho(idDespacho, despacho);
        if (d!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nDespacho actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }

    @DeleteMapping("/{idDespacho}")
    @Operation(summary = "Eliminar despacho", description = "Elimina un despacho del sistema por su identificador")
    public ResponseEntity<?> eliminarDespacho(@PathVariable Integer idDespacho){
        try{
            boolean d = ds.eliminarDespacho(idDespacho);
            if(d){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nDespacho eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }

}
