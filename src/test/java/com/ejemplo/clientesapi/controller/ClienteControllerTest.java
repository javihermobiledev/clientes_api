package com.ejemplo.clientesapi.controller;

import com.ejemplo.clientesapi.model.Cliente;
import com.ejemplo.clientesapi.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    public ClienteControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClienteById() {
        // Arrange
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNombre("Javier Hernandez");
        when(clienteService.getClienteById(clienteId)).thenReturn(cliente);

        // Act
        ResponseEntity<Cliente> response = clienteController.getClienteById(clienteId);

        // Assert
        assertEquals(cliente, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }
}
