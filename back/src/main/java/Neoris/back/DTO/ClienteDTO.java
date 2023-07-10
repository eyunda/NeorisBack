package Neoris.back.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    public String nombre;
    public String genero;
    public int edad;
    public String identificacion;
    public String direccion;
    public String telefono;
    public String contrasena;
    public boolean estado;

    public ClienteDTO() {
    }

    // Constructor con todos los campos
    public ClienteDTO(String nombre, String genero, int edad, String identificacion, String direccion, String telefono, String contrasena, boolean estado) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
    }


    public void setId(Long id) {
    }
}
