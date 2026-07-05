package cl.aridos.direccion.controller;

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

    @Operation(summary = "Listar regiones", description = "Lista todas las regiones")
    @GetMapping("/regiones")
    public List<Region> listarRegiones(){
        return ds.listarRegiones();
    }
    @Operation(summary = "Buscar región por ID", description = "Busca una región por su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Región encontrada"),
        @ApiResponse(responseCode = "404", description = "Región no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/regiones/{id}")
    public ResponseEntity<Region> buscarPorIdRegion(@PathVariable Integer id){
        try{
            Region r = ds.buscarPorIdRegion(id);
            return ResponseEntity.ok(r);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }        
    }
    @Operation(summary = "Crear región", description = "Crea una nueva región")
    @PostMapping("/regiones")
    public ResponseEntity<?> crearRegion(@RequestBody Region region){
        Region r = ds.guardarRegion(region);
        if (r!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nRegion creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }
    @Operation(summary = "Actualizar región", description = "Actualiza una región existente")
    @PutMapping("/regiones/{id}")
    public ResponseEntity<?> actualizarRegion(@PathVariable Integer id, @RequestBody Region region){
        Region r = ds.actualizarRegion(id, region);
        if (r!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nRegion actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }
    @Operation(summary = "Eliminar región", description = "Elimina una región por su ID")
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

    @Operation(summary = "Listar comunas", description = "Lista todas las comunas")
    @GetMapping("/comunas")
    public List<Comuna> listarComunas(){
        return ds.listarComunas();
    }
    @Operation(summary = "Buscar comuna por ID", description = "Busca una comuna por su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comuna encontrada"),
        @ApiResponse(responseCode = "404", description = "Comuna no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/comunas/{id}")
    public ResponseEntity<Comuna> buscarPorIdRComuna(@PathVariable Integer id){
        try{
            Comuna c = ds.buscarPorIdComuna(id);
            return ResponseEntity.ok(c);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }        
    }
    @Operation(summary = "Crear comuna", description = "Crea una nueva comuna")
    @PostMapping("/comunas")
    public ResponseEntity<?> crearComuna(@RequestBody Comuna comuna){
        Comuna c = ds.guardarComuna(comuna);
        if (c!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nComuna creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }
    @Operation(summary = "Actualizar comuna", description = "Actualiza una comuna existente")
    @PutMapping("/comunas/{id}")
    public ResponseEntity<?> actualizarComuna(@PathVariable Integer id,@RequestBody Comuna comuna){
        Comuna c = ds.actualizarComuna(id, comuna);
        if (c!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nComuna actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }
    @Operation(summary = "Eliminar comuna", description = "Elimina una comuna por su ID")
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

    @Operation(summary = "Listar direcciones", description = "Lista todas las direcciones")
    @GetMapping
    public List<Direccion> listarDirecciones(){
        return ds.listarDirecciones();
    }
    @Operation(summary = "Buscar dirección por ID", description = "Busca una dirección por su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección encontrada"),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> buscarPorIdDireccion(@PathVariable Integer id){
        try{
            Direccion d = ds.buscarPorIdDireccion(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }        
    }
    @Operation(summary = "Crear dirección", description = "Crea una nueva dirección")
    @PostMapping
    public ResponseEntity<?> crearDireccion(@RequestBody Direccion direccion){
        Direccion d = ds.guardarDireccion(direccion);
        if (d!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nDireccion creada exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }
    @Operation(summary = "Actualizar dirección", description = "Actualiza una dirección existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Integer id,@RequestBody Direccion direccion){
        Direccion d = ds.actualizarDireccion(id, direccion);
        if (d!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nDireccion actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }
    @Operation(summary = "Eliminar dirección", description = "Elimina una dirección por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Integer id){
        boolean d = ds.eliminarDireccion(id);
        if(d){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nDireccion eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }





    @Operation(summary = "Buscar dirección DTO por ID", description = "Busca una dirección DTO por su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección DTO encontrada"),
        @ApiResponse(responseCode = "404", description = "Dirección DTO no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/dto/{id}")
    public ResponseEntity<DireccionDTO> buscarDireccionDTO(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(ds.buscarDireccionDTO(id));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
