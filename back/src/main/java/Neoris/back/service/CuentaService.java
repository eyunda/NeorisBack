package Neoris.back.service;

import Neoris.back.DTO.ClienteDTO;
import Neoris.back.DTO.ClienteDTOResponse;
import Neoris.back.DTO.CuentaDTO;
import Neoris.back.DTO.CuentaDTOResponse;

import java.util.List;

public interface CuentaService {
    List<CuentaDTOResponse> obtenerCuenta();
    CuentaDTO obtenerCuentaPorId(Long id);
    CuentaDTO crearCuenta(CuentaDTO cuentaDTO);
    CuentaDTO actualizarCuenta(Long id, CuentaDTO cuentaDTO);
    void eliminarCuenta(Long id);
}
