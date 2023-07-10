package Neoris.back.repository;

import Neoris.back.DTO.ClienteDTOResponse;
import Neoris.back.DTO.CuentaDTOResponse;
import Neoris.back.DTO.ResponseCuenta;
import Neoris.back.entity.Cuenta;
import Neoris.back.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @Query(value = "" +
            "SELECT c.id, p.nombre, c.numero_cuenta, c.tipo_cuenta, c.saldo_inicial, c.estado " +
            "FROM cuenta c " +
            "JOIN persona p ON c.nombre_cliente = p.nombre " +
            "", nativeQuery = true)
    List<CuentaDTOResponse> obtenerCuenta();

    @Query(value = "" +
            "SELECT c.numero_Cuenta, c.tipo_Cuenta, c.estado, m.fecha, m.tipo_movimiento, m.valor, m.saldo, m.nombre " +
            "FROM cuenta c " +
            "JOIN movimientos m ON c.nombre_cliente = m.nombre " +
            "WHERE c.numero_cuenta = :numero_cuenta " +
            "ORDER BY m.fecha DESC " +
            "LIMIT 1;" +
            "", nativeQuery = true)
    Optional<ResponseCuenta> obtenerMoviento(@Param("numero_cuenta") Integer numero_cuenta);

    @Query(value = "" +
            "SELECT c.* " +
            "FROM cuenta c " +
            "WHERE c.numero_cuenta = :numero_cuenta " +
            "", nativeQuery = true)
    Optional<Cuenta> obtenerDatos(@Param("numero_cuenta") Integer numero_cuenta);
}
