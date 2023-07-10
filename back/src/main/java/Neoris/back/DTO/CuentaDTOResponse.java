package Neoris.back.DTO;

public interface CuentaDTOResponse {

    Integer getId();
    String getNumero_cuenta();
    String getNombre();
    double getSaldo_inicial();
    String getTipo_cuenta();
    boolean getEstado();

}
