package Neoris.back.service;

import Neoris.back.DTO.MovimientoDTORequest;
import Neoris.back.DTO.MovimientosDTO;
import Neoris.back.DTO.MovimientosDTOResponse;
import Neoris.back.DTO.ResponseMovimientos;

import java.util.List;

public interface MovimientosService {

    List<MovimientosDTOResponse> obtenerMovimientos();
    List<MovimientosDTOResponse> obtenerMovimientoPorId (Long id);
    MovimientosDTO crearMovimiento(MovimientoDTORequest movimientoDTORequest);
    MovimientosDTO actualizarMovimiento(Long id, MovimientosDTO movimientosDTO);
    void eliminarMoviento(Long id);
}
