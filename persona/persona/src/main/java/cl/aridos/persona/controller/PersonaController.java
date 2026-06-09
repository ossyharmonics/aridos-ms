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

@RestController
@RequestMapping("/aridos/personas")
public class PersonaController {
    @Autowired
    private PersonaService ps;

    @GetMapping
    public ResponseEntity<?> listarPersonas(){
        List<Persona> personas = ps.listarPersonas();
        if (personas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR 404. \nNo se han encontrado personas en la base de datos.");
        }
        return ResponseEntity.ok(personas);
    }
    @GetMapping("/{rut}")
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
    public ResponseEntity<?> guardarPersona(@RequestBody Persona p){
        Persona pNuevo = ps.guardarPersona(p);
        if (pNuevo != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Persona creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400. No se ha podido ingresar el paciente nuevo.");
        }
    }
    @PutMapping("/{rut}")
    public ResponseEntity<Persona> actualizar(@PathVariable String rut, @RequestBody Persona p){
        try{
            Persona px = ps.actualizarPersona(rut, p);
            return ResponseEntity.ok(px);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{rut}")
    public ResponseEntity<?> eliminarPersona(@PathVariable String rut){
        try{
            ps.eliminarPersona(rut);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<PersonaDTO> buscarPersonaDTO(@PathVariable String rut){
        try{
            return ResponseEntity.ok(ps.buscarPersonaDTO(rut));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
