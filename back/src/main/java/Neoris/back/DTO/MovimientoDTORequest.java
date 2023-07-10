package Neoris.back.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDTORequest {
    private String tipo_movimiento;
    private Integer numero_cuenta;
    private double valor;

}
