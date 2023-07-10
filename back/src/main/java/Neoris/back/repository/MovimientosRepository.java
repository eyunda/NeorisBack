package Neoris.back.repository;

import Neoris.back.DTO.CuentaDTOResponse;
import Neoris.back.DTO.MovimientosDTO;
import Neoris.back.DTO.MovimientosDTOResponse;
import Neoris.back.DTO.ResponseMovimientos;
import Neoris.back.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
    @Query(value = "" +
            "SELECT m.id, m.fecha, m.tipo_movimiento, m.valor, m.saldo, m.nombre, c.numero_Cuenta, c.tipo_Cuenta, c.saldo_inicial, c.estado " +
            "FROM movimientos m " +
            "JOIN cuenta c ON m.numero_cuenta = c.numero_Cuenta " +
            "", nativeQuery = true)
    List<MovimientosDTOResponse> obtenerMovimientos();

    @Query(value = "SELECT m.id, m.fecha, m.tipo_movimiento, m.valor, m.saldo, m.nombre, c.numero_Cuenta, c.tipo_Cuenta, c.saldo_inicial, c.estado " +
            "FROM movimientos m " +
            "JOIN cuenta c ON m.numero_cuenta = c.numero_Cuenta " +
            "WHERE m.id = :id", nativeQuery = true)
    List<MovimientosDTOResponse> buscarid(@Param("id") Long id);
}
