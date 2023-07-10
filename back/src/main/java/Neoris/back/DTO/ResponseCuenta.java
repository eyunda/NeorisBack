package Neoris.back.DTO;

import java.util.Date;

public interface ResponseCuenta {
    String getNumero_cuenta();
    String getNombre();
    String getTipo_cuenta();
    boolean getEstado();
    Date getFecha();
    String getTipo_movimiento();
    double getSaldo();
    double getValor();
}
