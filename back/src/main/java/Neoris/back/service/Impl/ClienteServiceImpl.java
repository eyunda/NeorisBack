package Neoris.back.service.Impl;

import Neoris.back.DTO.ClienteDTO;
import Neoris.back.DTO.ClienteDTOResponse;
import Neoris.back.entity.Cliente;
import Neoris.back.entity.Persona;
import Neoris.back.repository.ClienteRepository;
import Neoris.back.repository.PersonaRepository;
import Neoris.back.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public List<ClienteDTOResponse> obtenerClientes() {
        return clienteRepository.obtenerClientes();

    }

    @Override
    public ClienteDTO obtenerClientePorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.map(this::mapClienteToDTO).orElse(null);
    }


    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Persona persona = new Persona();
        // Mapear los valores del DTO a la entidad Persona
        persona.setNombre(clienteDTO.getNombre());
        persona.setGenero(clienteDTO.getGenero());
        persona.setEdad(clienteDTO.getEdad());
        persona.setIdentificacion(clienteDTO.getIdentificacion());
        persona.setDireccion(clienteDTO.getDireccion());
        persona.setTelefono(clienteDTO.getTelefono());

        Persona personaGuardada = personaRepository.save(persona);

        Cliente cliente = new Cliente();
        // Mapear los valores del DTO a la entidad Cliente
        cliente.setPersona(personaGuardada);
        cliente.setContrasena(clienteDTO.getContrasena());
        cliente.setEstado(clienteDTO.isEstado());

        Cliente clienteCreado = clienteRepository.save(cliente);
        return mapClienteToDTO(clienteCreado);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            Persona persona = cliente.getPersona();
            // Actualizar los valores de la persona con los del DTO
            persona.setNombre(clienteDTO.getNombre());
            persona.setGenero(clienteDTO.getGenero());
            persona.setEdad(clienteDTO.getEdad());
            persona.setIdentificacion(clienteDTO.getIdentificacion());
            persona.setDireccion(clienteDTO.getDireccion());
            persona.setTelefono(clienteDTO.getTelefono());
            Persona personaActualizada = personaRepository.save(persona);
            // Actualizar los valores del cliente con los del DTO
            cliente.setPersona(personaActualizada);
            cliente.setContrasena(clienteDTO.getContrasena());
            cliente.setEstado(clienteDTO.isEstado());
            cliente.setEstado(clienteDTO.isEstado());

            Cliente clienteActualizado = clienteRepository.save(cliente);


            return mapClienteToDTO(clienteActualizado);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }


    private ClienteDTO mapClienteToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        // Mapear los valores de la entidad Cliente a ClienteDTO
        clienteDTO.setNombre(cliente.getPersona().getNombre());
        clienteDTO.setGenero(cliente.getPersona().getGenero());
        clienteDTO.setEdad(cliente.getPersona().getEdad());
        clienteDTO.setIdentificacion(cliente.getPersona().getIdentificacion());
        clienteDTO.setDireccion(cliente.getPersona().getDireccion());
        clienteDTO.setTelefono(cliente.getPersona().getTelefono());
        clienteDTO.setContrasena(cliente.getContrasena());
        clienteDTO.setEstado(cliente.isEstado());

        return clienteDTO;
    }


}
