package cl.aridos.pago.controller;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import cl.aridos.pago.dto.PagoDTO;
import cl.aridos.pago.model.Pago;
import cl.aridos.pago.model.TipoPago;
import cl.aridos.pago.service.PagoService;

@RestController
@RequestMapping("/aridos/pagos")
public class PagoController {

    @Autowired
        private PagoService ps;

    @GetMapping
    @Operation(summary = "Listar pagos", description = "Lista todos los pagos registrados")
    public List<Pago> listarPagos(){
        return ps.listarPagos();
    } 

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pago por ID", description = "Busca un pago por su identificador unico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Pago> buscarIdPago(@PathVariable Integer id){
        try
        {
            Pago p = ps.buscarPorIdPago(id);
            return ResponseEntity.ok(p);
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear pago", description = "Crea un nuevo pago en el sistema")
    public ResponseEntity<?> guardarPago(@RequestBody Pago pago){
        Pago p = ps.guardarPago(pago);
        if (p != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("201 \nPago creado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400. No se ha podido ingresar el pago nuevo.");
        }
    }


//  ***************************TIPO DE PAGO***************************
    
    @GetMapping("/tipo-pagos")
    @Operation(summary = "Listar tipos de pago", description = "Lista todos los tipos de pago registrados")
    public List<TipoPago> listarTipoPagos(){
        return ps.listarTipoPagos();
    }
    @GetMapping("/tipo-pago/{id}")
    @Operation(summary = "Buscar tipo de pago por ID", description = "Busca un tipo de pago por su identificador unico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Tipo de pago no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<TipoPago> buscarIdTipoPago(@PathVariable Integer id){
        try
        {
            TipoPago tp = ps.buscarPorIdTipoPago(id);
            return ResponseEntity.ok(tp);
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/tipo-pagos/")
    @Operation(summary = "Crear tipo de pago", description = "Crea un nuevo tipo de pago en el sistema")
    public ResponseEntity<?> crearTipoPago(@RequestBody TipoPago tipoPago){
        TipoPago tp = ps.guardarTipoPago(tipoPago);
        if (tp != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Tipo de pago creado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400. No se ha podido ingresar el tipo de pago nuevo.");
        }
    }
    @PutMapping("/tipo-pagos/{id}")
    @Operation(summary = "Actualizar tipo de pago", description = "Actualiza un tipo de pago existente por su ID")
    public ResponseEntity<?> actualizarTipoPago(@PathVariable Integer id, @RequestBody TipoPago tipoPago){
        TipoPago tp = ps.actualizarTipoPago(id, tipoPago);
        if (tp != null) {
            return ResponseEntity.ok("Tipo de pago actualizado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR 404. No se ha podido actualizar el tipo de pago.");
        }
    }
    @DeleteMapping("/tipo-pagos/{id}")
    @Operation(summary = "Eliminar tipo de pago", description = "Elimina un tipo de pago por su identificador unico")
    public ResponseEntity<?> eliminarTipoPago(@PathVariable Integer id){
        boolean eliminado = ps.eliminarTipoPago(id);
        if (eliminado) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tipo de pago eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR 404. No se ha encontrado el id de tipo de pago, por lo que no se ha podido eliminar el tipo de pago.");
        }
    }



    @GetMapping("/dto/{id}")
    @Operation(summary = "Buscar pago DTO por ID", description = "Busca un pago DTO por su identificador unico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago DTO encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago DTO no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<PagoDTO> buscarPagoDTO(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(ps.buscarPagoDTO(id));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}



