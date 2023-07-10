package Neoris.back.DTO;

import java.util.Date;
public interface MovimientosDTOResponse {
    Long getId();
    Date getFecha();
    String getNombre();
    String getTipo_movimiento();
    String getNumero_cuenta();
    String getTipo_cuenta();
    String getSaldo_inicial();
    boolean getEstado();
    double getSaldo();
    double getValor();

}
