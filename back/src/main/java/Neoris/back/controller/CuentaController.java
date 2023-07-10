package Neoris.back.controller;

import Neoris.back.DTO.CuentaDTO;
import Neoris.back.DTO.CuentaDTOResponse;
import Neoris.back.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }
    @GetMapping
    public List<CuentaDTOResponse> obtenerClientes() {
        return cuentaService.obtenerCuenta();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> obtenerCuentaPorId(@PathVariable Long id) {
        CuentaDTO cuentaDTO = cuentaService.obtenerCuentaPorId(id);
        if (cuentaDTO != null) {
            return ResponseEntity.ok(cuentaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO cuentaCreado = cuentaService.crearCuenta(cuentaDTO);
        return new ResponseEntity<>(cuentaCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO cuentaActualizado = cuentaService.actualizarCuenta(id, cuentaDTO);
        if (cuentaActualizado != null) {
            return ResponseEntity.ok(cuentaActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
