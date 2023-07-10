package Neoris.back.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CuentaDTO {
    public String numero_cuenta;
    public String nombre_cliente;
    public double saldo_inicial;
    public String tipo_cuenta;
    public boolean estado;


    public void setId(long l) {
    }
}
