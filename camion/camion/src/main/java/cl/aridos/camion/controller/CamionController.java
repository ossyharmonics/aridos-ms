package cl.aridos.camion.controller;

import java.util.List;

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
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.aridos.camion.dto.CamionDTO;
import cl.aridos.camion.model.Camion;
import cl.aridos.camion.service.CamionService;

@RestController
@RequestMapping("/aridos/camiones")
public class CamionController {
    @Autowired
    private CamionService cs;

    @GetMapping
    @Operation(summary = "Listar camiones", description = "Lista todos los camiones registrados")
    public List<Camion> listarCamiones() {
        return cs.listar();
    }

    @GetMapping("/{patente}")
    @Operation(summary = "Buscar camion por patente", description = "Busca un camion por su patente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Camion encontrado"),
        @ApiResponse(responseCode = "404", description = "Camion no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Camion> obtener(@PathVariable String patente) {
        try{
            Camion c = cs.buscarPorPatenteCamion(patente);
            return ResponseEntity.ok(c);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear camion", description = "Crea un nuevo camion en el sistema")
    public ResponseEntity<?> crear(@RequestBody Camion camion) {
        Camion c = cs.guardarCamion(camion);
        if (c != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \n El camion se ha creado correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");    
        }
    }
    /*@PutMapping("/{id}")
    public ResponseEntity<?> actualizarCamion(@PathVariable String patente,@RequestBody Camion camion){
        Camion c = cs.actualizarCamion(patente, camion);
        if (c!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nCamion actualizado correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }*/

    @DeleteMapping("/{patente}")
    @Operation(summary = "Eliminar camion", description = "Elimina un camion por su patente")
    public ResponseEntity<?> eliminarCamion(@PathVariable String patente){
        try{
            boolean c = cs.eliminarCamion(patente);
            if(c){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nCamion eliminada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }

    /*// Endpoint de composicion: trae el conductor del camion via microservicio CONDUCTOR
    @GetMapping("/{patente}/conductor")
    public ResponseEntity<ConductorDTO> conductorDelCamion(@PathVariable String patente) {
        ConductorDTO dto = camionService.obtenerConductorDelCamion(patente);
        return dto == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(dto);
    }*/

    



    @GetMapping("/dto/{patente}")
    @Operation(summary = "Buscar camion DTO por patente", description = "Busca un camion DTO por su patente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Camion DTO encontrado"),
        @ApiResponse(responseCode = "404", description = "Camion DTO no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<CamionDTO> buscarCamionDTO(@PathVariable String patente){
        try{
            return ResponseEntity.ok(cs.buscarCamionDTO(patente));
        }catch(Exception w){
            return ResponseEntity.notFound().build();
        }
    }
}
