package Neoris.back.DTO;

public interface ClienteDTOResponse {
    Long getId();
    String getNombre();
    String getGenero();
    Integer getEdad();
    String getIdentificacion();
    String getDireccion();
    String getTelefono();
    String getContrasena();
    boolean getEstado();
}
