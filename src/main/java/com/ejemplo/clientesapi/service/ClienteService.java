package com.ejemplo.clientesapi.service;

import com.ejemplo.clientesapi.exception.ClienteAlreadyExistsException;
import com.ejemplo.clientesapi.model.Cliente;
import com.ejemplo.clientesapi.exception.ClienteNotFoundException;
import com.ejemplo.clientesapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    public Cliente saveCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            throw new ClienteAlreadyExistsException(cliente.getId());
        }
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        clienteRepository.deleteById(id);
    }
}
