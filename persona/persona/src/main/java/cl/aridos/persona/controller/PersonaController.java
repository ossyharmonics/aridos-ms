package cl.aridos.persona.controller;

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

import cl.aridos.persona.dto.PersonaDTO;
import cl.aridos.persona.model.Persona;
import cl.aridos.persona.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/aridos/personas")
public class PersonaController {
    @Autowired
    private PersonaService ps;

    @GetMapping
    @Operation(summary = "Listar personas", description = "Lista todas las personas registradas en el sistema")
    public ResponseEntity<?> listarPersonas(){
        List<Persona> personas = ps.listarPersonas();
        if (personas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR 404. \nNo se han encontrado personas en la base de datos.");
        }
        return ResponseEntity.ok(personas);
    }
    @GetMapping("/{rut}")
    @Operation(summary = "Buscar persona por rut", description = "Busca una persona por su rut")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Persona encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Persona no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Persona> buscarPorRut(@PathVariable String rut){
        try
        {
            Persona p = ps.buscarPorRut(rut);
            return ResponseEntity.ok(p);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @Operation(summary = "Crear persona", description = "Crea una nueva persona en el sistema")
    public ResponseEntity<?> guardarPersona(@RequestBody Persona p){
        Persona pNuevo = ps.guardarPersona(p);
        if (pNuevo != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Persona creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400. No se ha podido ingresar el paciente nuevo.");
        }
    }
    @PutMapping("/{rut}")
    @Operation(summary = "Actualizar persona", description = "Actualiza los datos de una persona existente")
    public ResponseEntity<Persona> actualizar(@PathVariable String rut, @RequestBody Persona p){
        try{
            Persona px = ps.actualizarPersona(rut, p);
            return ResponseEntity.ok(px);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{rut}")
    @Operation(summary = "Eliminar persona", description = "Elimina una persona del sistema")
    public ResponseEntity<?> eliminarPersona(@PathVariable String rut){
        boolean eliminado = ps.eliminarPersona(rut);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }

    @GetMapping("/dto/{rut}")
    @Operation(summary = "Buscar persona DTO por rut", description = "Busca los datos DTO de una persona por su rut")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Persona DTO encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Persona no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<PersonaDTO> buscarPersonaDTO(@PathVariable String rut){
        try{
            return ResponseEntity.ok(ps.buscarPersonaDTO(rut));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
