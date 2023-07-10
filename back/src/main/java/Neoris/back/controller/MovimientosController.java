package Neoris.back.controller;

import Neoris.back.DTO.*;
import Neoris.back.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("movimientos")
public class MovimientosController {
    @Autowired
    MovimientosService movimientosService;

    public MovimientosController( MovimientosService movimientosService) {
        this.movimientosService = movimientosService;
    }
    @GetMapping
    public List<MovimientosDTOResponse> obtenerMovientos() {
        return movimientosService.obtenerMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MovimientosDTOResponse>> obtenerCuentaPorId(@PathVariable Long id) {
        List<MovimientosDTOResponse> response = movimientosService.obtenerMovimientoPorId(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<MovimientosDTO> crearCuenta( @RequestBody MovimientoDTORequest movimientoDTORequest) {
        MovimientosDTO movimientosAgregados = movimientosService.crearMovimiento(movimientoDTORequest);
        return new ResponseEntity<>(movimientosAgregados, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientosDTO> actualizarCuenta(@PathVariable Long id, @RequestBody MovimientosDTO movimientosDTO) {
        MovimientosDTO movientoActualizado = movimientosService.actualizarMovimiento(id, movimientosDTO);
        if (movientoActualizado != null) {
            return ResponseEntity.ok(movientoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        movimientosService.eliminarMoviento(id);
        return ResponseEntity.noContent().build();
    }
}
