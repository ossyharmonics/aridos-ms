package cl.aridos.despacho.controller;

//import cl.aridos.despacho.dto.CamionDTO;
import cl.aridos.despacho.model.Despacho;
import cl.aridos.despacho.service.DespachoService;

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
    public List<Despacho> listar() {
        return ds.listar();
    }

    @GetMapping("/{idDespacho}")
    public ResponseEntity<Despacho> buscarPorIdDespacho(@PathVariable Integer idDespacho) {
        try{
            Despacho s = ds.buscarPorIdDespacho(idDespacho);
            return ResponseEntity.ok(s);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }       
    }

    @PostMapping
    public ResponseEntity<?> crearDespacho(@RequestBody Despacho despacho){
        Despacho d = ds.guardarDespacho(despacho);
        if (d != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nDespacho creado exitosamente.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al crear.");
        }
    }

    @PutMapping("/{idDespacho}")
    public ResponseEntity<?> actualizarDespacho(@PathVariable Integer idDespacho, @RequestBody Despacho despacho){
        Despacho d = ds.actualizarDespacho(idDespacho, despacho);
        if (d!= null){
            return ResponseEntity.status(HttpStatus.OK).body("201 \nDespacho actualizada correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }

    @DeleteMapping("/{idDespacho}")
    public ResponseEntity<?> eliminarDespacho(@PathVariable Integer idDespacho){
        boolean d = ds.eliminarDespacho(idDespacho);
        if(d){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 \nDespacho eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }

    /*// Endpoint de composicion: trae el camion del despacho via microservicio CAMION
    @GetMapping("/{idDespacho}/camion")
    public ResponseEntity<CamionDTO> camionDelDespacho(@PathVariable Long idDespacho) {
        CamionDTO dto = despachoService.obtenerCamionDelDespacho(idDespacho);
        return dto == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(dto);
    }*/
}
