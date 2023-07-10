package Neoris.back.service.Impl;

import Neoris.back.DTO.*;
import Neoris.back.entity.Cuenta;
import Neoris.back.entity.Movimientos;
import Neoris.back.message.SaldoNoDisponibleException;
import Neoris.back.message.NoSuchElementException;
import Neoris.back.repository.CuentaRepository;
import Neoris.back.repository.MovimientosRepository;
import Neoris.back.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovientosServiceImpl implements MovimientosService {

    private final MovimientosRepository movimientosRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    public MovientosServiceImpl(MovimientosRepository movimientosRepository){this.movimientosRepository = movimientosRepository; }

    @Override
    public List<MovimientosDTOResponse> obtenerMovimientos() {
        return movimientosRepository.obtenerMovimientos();
    }

    @Override
    public List<MovimientosDTOResponse> obtenerMovimientoPorId(Long id) {
        List<MovimientosDTOResponse> movimientoOptional = movimientosRepository.buscarid(id);
        return movimientoOptional;
    }

    @Override
    public MovimientosDTO crearMovimiento(MovimientoDTORequest movimientosDTORequest) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.obtenerDatos(movimientosDTORequest.getNumero_cuenta());
        Optional<ResponseCuenta> cuentaResponse = cuentaRepository.obtenerMoviento(movimientosDTORequest.getNumero_cuenta());
        if (cuentaOptional.isPresent()) {

                Cuenta responseCuenta = cuentaOptional.get();
                double saldoInicial = responseCuenta.getSaldo_inicial();
                String nombre_cliente = responseCuenta.getNombre_cliente();
                double valorMovimiento = movimientosDTORequest.getValor();
                double saldoActualizado;

                if (movimientosDTORequest.getTipo_movimiento().equals("retiro")) {
                    if (valorMovimiento > saldoInicial) {
                        throw new SaldoNoDisponibleException("Saldo no disponible");
                    }
                    saldoActualizado = saldoInicial - valorMovimiento;
                } else if (movimientosDTORequest.getTipo_movimiento().equals("deposito")) {
                    saldoActualizado = saldoInicial + valorMovimiento;
                } else {
                    throw new IllegalArgumentException("Tipo de movimiento inválido");
                }

                Movimientos movimientos = new Movimientos();
                movimientos.setValor(valorMovimiento);
                movimientos.setTipoMovimiento(movimientosDTORequest.getTipo_movimiento());
                movimientos.setNombre(nombre_cliente);
                movimientos.setSaldo(saldoActualizado);
                movimientos.setNumero_cuenta(movimientosDTORequest.getNumero_cuenta());
                Movimientos movimientosGuardados = movimientosRepository.save(movimientos);
                return mapMovimientosToDTO(movimientosGuardados);

        }
        else if(cuentaResponse.isPresent()){
            ResponseCuenta responseCuenta = cuentaResponse.get();
            double saldoInicial = responseCuenta.getSaldo();
            String nombre_clientes = responseCuenta.getNombre();
            double valorMovimiento = movimientosDTORequest.getValor();
            double saldoActualizado;

            if (movimientosDTORequest.getTipo_movimiento().equals("retiro")) {
                if (valorMovimiento > saldoInicial) {
                    throw new SaldoNoDisponibleException("Saldo no disponible");
                }
                saldoActualizado = saldoInicial - valorMovimiento;
            } else if (movimientosDTORequest.getTipo_movimiento().equals("deposito")) {
                saldoActualizado = saldoInicial + valorMovimiento;
            } else {
                throw new IllegalArgumentException("Tipo de movimiento inválido");
            }
            Movimientos movimientos = new Movimientos();
            movimientos.setValor(valorMovimiento);
            movimientos.setTipoMovimiento(movimientosDTORequest.getTipo_movimiento());
            movimientos.setNombre(nombre_clientes);
            movimientos.setNumero_cuenta(movimientosDTORequest.getNumero_cuenta());
            movimientos.setSaldo(saldoActualizado);

            Movimientos movimientosGuardados = movimientosRepository.save(movimientos);
            return mapMovimientosToDTO(movimientosGuardados);

        }
        else {
            throw new NoSuchElementException("Cuenta no encontrada");
        }
    }

    @Override
    public MovimientosDTO actualizarMovimiento(Long id, MovimientosDTO movimientosDTO) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            double saldoInicial = cuenta.getSaldo_inicial();
            double valorMovimiento = movimientosDTO.getValor();
            double saldoActualizado;

            if (movimientosDTO.getTipo_movimiento().equals("retiro")) {
                if (valorMovimiento > saldoInicial) {
                    throw new SaldoNoDisponibleException("Saldo no disponible");
                }
                saldoActualizado = saldoInicial - valorMovimiento;
            } else if (movimientosDTO.getTipo_movimiento().equals("deposito")) {
                saldoActualizado = saldoInicial + valorMovimiento;
            } else {
                throw new IllegalArgumentException("Tipo de movimiento inválido");
            }

            Movimientos movimientos = new Movimientos();
            movimientos.setValor(valorMovimiento);
            movimientos.setTipoMovimiento(movimientosDTO.getTipo_movimiento());
            movimientos.setNombre(movimientosDTO.getNombre());
            movimientos.setSaldo(saldoActualizado);

            Movimientos movimientosGuardados = movimientosRepository.save(movimientos);
            return mapMovimientosToDTO(movimientosGuardados);
        } else {
            throw new NoSuchElementException("Cuenta no encontrada");
        }
    }

    @Override
    public void eliminarMoviento(Long id) {
        movimientosRepository.deleteById(id);
    }

    private MovimientosDTO mapMovimientosToDTO(Movimientos movimientos) {
        MovimientosDTO movimientosDTO = new MovimientosDTO();
        movimientosDTO.setValor(movimientos.getValor());
        movimientosDTO.setTipo_movimiento(movimientos.getTipoMovimiento());
        movimientosDTO.setNombre(movimientos.getNombre());
        movimientosDTO.setSaldo(movimientos.getSaldo());
        return movimientosDTO;
    }
}
