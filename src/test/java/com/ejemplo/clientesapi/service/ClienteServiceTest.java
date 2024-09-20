package com.ejemplo.clientesapi.service;

import com.ejemplo.clientesapi.model.Cliente;
import com.ejemplo.clientesapi.exception.ClienteNotFoundException;
import com.ejemplo.clientesapi.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClienteById() {
        // Arrange
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNombre("Alyssa Hernandez");
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = clienteService.getClienteById(clienteId);

        // Assert
        assertNotNull(result);
        assertEquals(clienteId, result.getId());
        assertEquals("Alyssa Hernandez", result.getNombre());
    }

    @Test
    void testGetClienteByIdNotFound() {
        // Arrange
        Long clienteId = 2L;
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ClienteNotFoundException.class, () -> clienteService.getClienteById(clienteId));
    }
}
