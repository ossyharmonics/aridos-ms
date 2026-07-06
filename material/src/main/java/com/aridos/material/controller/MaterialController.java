package com.aridos.material.controller;

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

import com.aridos.material.dto.MaterialDTO;
import com.aridos.material.model.Material;
import com.aridos.material.service.MaterialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/aridos/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    @Operation(summary = "Listar materiales", description = "Lista todos los materiales registrados en el sistema")
    public ResponseEntity<?> listarMateriales() {
        List<Material> materiales = materialService.listarMateriales();
        if (materiales.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR 404. \nNo se han encontrado materiales en la base de datos.");
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar material por ID", description = "Busca un material por su identificador unico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Material> buscarPorId(@PathVariable Integer id) {
        try {
            Material m = materialService.buscarPorId(id);
            return ResponseEntity.ok(m);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear material", description = "Crea un nuevo material en el sistema")
    public ResponseEntity<?> guardarMaterial(@RequestBody Material material) {
        Material m = materialService.guardarMaterial(material);
        if (m != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Material creado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400. No se ha podido ingresar el material nuevo.");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar material", description = "Actualiza los datos de un material existente")
    public ResponseEntity<?> actualizarMaterial(@PathVariable Integer id, @RequestBody Material material) {
        Material m = materialService.actualizarMaterial(id, material);
        if (m != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Material actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nError al actualizar.");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar material", description = "Elimina un material del sistema")
    public ResponseEntity<?> eliminarMaterial(@PathVariable Integer id) {
        boolean eliminado = materialService.eliminarMaterial(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR 400 \nLa solicitud no ha podido ser procesada.");
        }
    }

    @GetMapping("/dto/{id}")
    @Operation(summary = "Buscar material DTO por ID", description = "Busca los datos DTO de un material por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material DTO encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MaterialDTO> buscarMaterialDTO(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(materialService.buscarMaterialDTO(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
