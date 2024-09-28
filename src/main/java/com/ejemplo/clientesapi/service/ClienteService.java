package com.ejemplo.clientesapi.service;

import com.ejemplo.clientesapi.exception.ClienteAlreadyExistsException;
import com.ejemplo.clientesapi.exception.ClienteNotFoundException;
import com.ejemplo.clientesapi.model.Cliente;
import com.ejemplo.clientesapi.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    // Obtener cliente por ID
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    // Crear cliente
    public Cliente crearCliente(@Valid Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            throw new ClienteAlreadyExistsException(cliente.getId());
        }
        return clienteRepository.save(cliente);
    }

    // Actualizar cliente
    public Cliente actualizarCliente(Long id, Cliente clienteDetalles) {
        Cliente cliente = obtenerClientePorId(id);

        cliente.setNombre(clienteDetalles.getNombre());
        cliente.setEmail(clienteDetalles.getEmail());
        cliente.setTelefono(clienteDetalles.getTelefono());
        cliente.setDireccion(clienteDetalles.getDireccion());
        cliente.setFechaNacimiento(clienteDetalles.getFechaNacimiento());
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(clienteDetalles.getActivo());

        return clienteRepository.save(cliente);

    }

    // Eliminar cliente
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        clienteRepository.deleteById(id);
    }
}
