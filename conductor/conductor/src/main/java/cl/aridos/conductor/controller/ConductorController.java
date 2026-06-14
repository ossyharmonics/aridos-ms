package cl.aridos.conductor.controller;

import cl.aridos.conductor.dto.ConductorDTO;
//import cl.aridos.conductor.dto.CamionDTO;
import cl.aridos.conductor.model.Conductor;
import cl.aridos.conductor.service.ConductorService;

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

import java.util.List;

@RestController
@RequestMapping("/aridos/conductores")
public class ConductorController {
    @Autowired
    private ConductorService cs;


    @GetMapping
    public List<Conductor> listar() {
        return cs.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conductor> buscarPorIdCOnductor(@PathVariable Integer id) {
        try{
            Conductor c = cs.buscarPorIdConductor(id);
            return ResponseEntity.ok(c);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearConductor(@RequestBody Conductor conductor) {
        Conductor c = cs.guardarConductor(conductor);
        if (c != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \n El conductor se ha creado correctamente");
            }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }    
    /*@PutMapping("/{id}")
    public ResponseEntity<?> actualizarConductor(@PathVariable Integer id,@RequestBody Conductor conductor){
        Conductor c = cs.actualizarConductor(id, conductor);
        if (c!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nConductor actualizado correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarConductor(@PathVariable Integer id, Conductor conductor){
        boolean c = cs.eliminarConductor(id);
        if(c){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nConductor eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }


    @GetMapping("/dto/{id}")
    public ResponseEntity<?> buscarConductorDTO(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(cs.buscarConductorDTO(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR 404\nEl id conductor no existe.");
        }
    }
}