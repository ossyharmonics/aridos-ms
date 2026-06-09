package cl.aridos.direccion.controller;

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

import cl.aridos.direccion.dto.DireccionDTO;
import cl.aridos.direccion.model.Comuna;
import cl.aridos.direccion.model.Direccion;
import cl.aridos.direccion.model.Region;
import cl.aridos.direccion.service.DireccionService;

@RestController
@RequestMapping("/aridos/direcciones")
public class DireccionController {
    @Autowired
    private DireccionService ds;


    //***********************REGIONES***********************

    @GetMapping("/regiones")
    public List<Region> listarRegiones(){
        return ds.listarRegiones();
    }
    @GetMapping("/regiones/{id}")
    public ResponseEntity<Region> buscarPorIdRegion(@PathVariable Integer id){
        try{
            Region r = ds.buscarPorIdRegion(id);
            return ResponseEntity.ok(r);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }        
    }
    @PostMapping("/regiones")
    public ResponseEntity<?> crearRegion(@RequestBody Region region){
        Region r = ds.guardarRegion(region);
        if (r!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nRegion creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }
    @PutMapping("/regiones/{id}")
    public ResponseEntity<?> actualizarRegion(@PathVariable Integer id, @RequestBody Region region){
        Region r = ds.actualizarRegion(id, region);
        if (r!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nRegion actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }
    @DeleteMapping("/regiones/{id}")
    public ResponseEntity<?> eliminarRegion(@PathVariable Integer id){
        boolean r = ds.eliminarRegion(id);
        if(r){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nRegion eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }


    //***********************COMUNAS***********************

    @GetMapping("/comunas")
    public List<Comuna> listarComunas(){
        return ds.listarComunas();
    }
    @GetMapping("/comunas/{id}")
    public ResponseEntity<Comuna> buscarPorIdRComuna(@PathVariable Integer id){
        try{
            Comuna c = ds.buscarPorIdComuna(id);
            return ResponseEntity.ok(c);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }        
    }
    @PostMapping("/comunas")
    public ResponseEntity<?> crearComuna(@RequestBody Comuna comuna){
        Comuna c = ds.guardarComuna(comuna);
        if (c!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nComuna creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }
    @PutMapping("/comunas/{id}")
    public ResponseEntity<?> actualizarComuna(@PathVariable Integer id,@RequestBody Comuna comuna){
        Comuna c = ds.actualizarComuna(id, comuna);
        if (c!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nComuna actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }
    @DeleteMapping("/comunas/{id}")
    public ResponseEntity<?> eliminarComuna(@PathVariable Integer id){
        boolean c = ds.eliminarComuna(id);
        if(c){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nComuna eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }


    //***********************DIRECCIONES***********************

    @GetMapping
    public List<Direccion> listarDirecciones(){
        return ds.listarDirecciones();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> buscarPorIdDireccion(@PathVariable Integer id){
        try{
            Direccion d = ds.buscarPorIdDireccion(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }        
    }
    @PostMapping
    public ResponseEntity<?> crearDireccion(@RequestBody Direccion direccion){
        Direccion d = ds.guardarDireccion(direccion);
        if (d!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nDireccion creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Integer id,@RequestBody Direccion direccion){
        Direccion d = ds.actualizarDireccion(id, direccion);
        if (d!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nDireccion actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Integer id, Direccion direccion){
        boolean d = ds.eliminarDireccion(id);
        if(d){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nDireccion eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }





    @GetMapping("/dto/{id}")
    public ResponseEntity<DireccionDTO> buscarDireccionDTO(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(ds.buscarDireccionDTO(id));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
