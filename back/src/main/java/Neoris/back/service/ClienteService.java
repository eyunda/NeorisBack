package Neoris.back.service;

import Neoris.back.DTO.ClienteDTO;
import Neoris.back.DTO.ClienteDTOResponse;
import Neoris.back.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<ClienteDTOResponse> obtenerClientes();
    ClienteDTO obtenerClientePorId(Long id);
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO);
    void eliminarCliente(Long id);

}
