package Neoris.back.service.Impl;

import Neoris.back.DTO.CuentaDTO;
import Neoris.back.DTO.CuentaDTOResponse;
import Neoris.back.entity.Cuenta;
import Neoris.back.repository.CuentaRepository;
import Neoris.back.service.CuentaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public List<CuentaDTOResponse> obtenerCuenta() {
        return cuentaRepository.obtenerCuenta();
    }

    @Override
    public CuentaDTO obtenerCuentaPorId(Long id) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        return cuentaOptional.map(this::mapCuentaToDTO).orElse(null);
    }

    @Override
    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        // Mapear los valores del DTO a la entidad Cuenta
        cuenta.setNombre_cliente(cuentaDTO.getNombre_cliente());
        cuenta.setNumero_cuenta(cuentaDTO.getNumero_cuenta());
        cuenta.setTipo_cuenta(cuentaDTO.getTipo_cuenta());
        cuenta.setSaldo_inicial(cuentaDTO.getSaldo_inicial());
        cuenta.setEstado(cuentaDTO.isEstado());
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);

        return mapCuentaToDTO(cuentaGuardada);
    }

    @Override
    public CuentaDTO actualizarCuenta(Long id, CuentaDTO cuentaDTO) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            // Actualizar los valores de la persona con los del DTO
            cuenta.setNombre_cliente(cuentaDTO.getNombre_cliente());
            cuenta.setNumero_cuenta(cuentaDTO.getNumero_cuenta());
            cuenta.setTipo_cuenta(cuentaDTO.getTipo_cuenta());
            cuenta.setSaldo_inicial(cuentaDTO.getSaldo_inicial());
            cuenta.setEstado(cuentaDTO.isEstado());
            Cuenta cuentaActualizado = cuentaRepository.save(cuenta);
            return mapCuentaToDTO(cuentaActualizado);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    private CuentaDTO mapCuentaToDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();

        // Mapear los valores de la entidad Cuenta a CuentaDTO
        cuentaDTO.setNumero_cuenta(cuenta.getNumero_cuenta());
        cuentaDTO.setTipo_cuenta(cuenta.getTipo_cuenta());
        cuentaDTO.setSaldo_inicial(cuenta.getSaldo_inicial());
        cuentaDTO.setNombre_cliente(cuenta.getNombre_cliente());
        cuentaDTO.setEstado(cuenta.isEstado());

        return cuentaDTO;
    }

}
