package com.ejemplo.clientesapi.controller;

import com.ejemplo.clientesapi.model.Cliente;
import com.ejemplo.clientesapi.service.ClienteService;
import com.ejemplo.clientesapi.exception.ClienteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @Test
    void testObtenerClientePorId() {
        // Arrange
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNombre("Javier Hernandez");
        cliente.setEmail("javier.hernandez@example.com");
        cliente.setTelefono("123-456-7890");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setFechaNacimiento(LocalDate.of(1985,10,15));
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);
        when(clienteService.obtenerClientePorId(clienteId)).thenReturn(cliente);

        // Act
        ResponseEntity<Cliente> response = clienteController.obtenerClientePorId(clienteId);

        // Assert
        assertEquals(clienteId, response.getBody().getId());
        assertEquals("Javier Hernandez", response.getBody().getNombre());
        assertEquals("javier.hernandez@example.com", response.getBody().getEmail());
        assertEquals("123-456-7890", response.getBody().getTelefono());
        assertEquals("Calle Falsa 123", response.getBody().getDireccion());
        assertEquals(LocalDate.of(1985,10,15), response.getBody().getFechaNacimiento());
        assertNotNull(response.getBody().getFechaRegistro());
        assertTrue(response.getBody().getActivo());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testObtenerClientePorIdNotFound() {
        // Arrange
        Long clienteId = 1L;
        when(clienteService.obtenerClientePorId(clienteId)).thenThrow(new ClienteNotFoundException(clienteId));

        // Act & Assert
        assertThrows(ClienteNotFoundException.class, () -> clienteController.obtenerClientePorId(clienteId));
    }

    @Test
    void testCrearCliente() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Mauricio Hernandez");
        cliente.setEmail("mauricio.hernandez@example.com");
        cliente.setTelefono("172-106-7590");
        cliente.setDireccion("loc. el consuelo");
        cliente.setFechaNacimiento(LocalDate.of(1980,02,20));
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);
        when(clienteService.crearCliente(cliente)).thenReturn(cliente);

        // Act
        ResponseEntity<Cliente> response = clienteController.crearCliente(cliente);

        // Assert
        assertEquals(201, response.getStatusCode().value());
        assertEquals(cliente.getNombre(), response.getBody().getNombre());
        assertEquals(cliente.getEmail(), response.getBody().getEmail());
        assertEquals(cliente.getTelefono(), response.getBody().getTelefono());
        assertEquals(cliente.getDireccion(), response.getBody().getDireccion());
        assertEquals(cliente.getFechaNacimiento(), response.getBody().getFechaNacimiento());
        assertEquals(cliente.getFechaRegistro(), response.getBody().getFechaRegistro());
        assertEquals(cliente.getActivo(), response.getBody().getActivo());
    }
    @Test
    void testActualizarCliente() {
        // Arrange
        Long clienteId = 1L;

        // Cliente existente que está registrado en la base de datos
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(clienteId);
        clienteExistente.setNombre("Javier Hernandez");
        clienteExistente.setEmail("javier.hernandez@example.com");
        clienteExistente.setTelefono("123-456-7890");
        clienteExistente.setDireccion("Calle Falsa 123");
        clienteExistente.setFechaNacimiento(LocalDate.of(1985,10,15));
        LocalDateTime fechaRegistro = LocalDateTime.now();
        clienteExistente.setFechaRegistro(fechaRegistro);
        clienteExistente.setActivo(true);

        // Nuevos detalles del cliente para la actualización
        Cliente clienteDetalles = new Cliente();
        clienteDetalles.setNombre("Javier Hernandez");
        clienteDetalles.setEmail("javier.hernandez@example.com");
        clienteDetalles.setTelefono("173-106-4388");
        clienteDetalles.setDireccion("Av. 5 de mayo S/n");
        clienteDetalles.setFechaNacimiento(LocalDate.of(1985,10,15));
        clienteDetalles.setFechaRegistro(fechaRegistro);
        clienteDetalles.setActivo(true);

        // Simulación de obtener el cliente existente
        when(clienteService.obtenerClientePorId(clienteId)).thenReturn(clienteExistente);
        // Simulación de la actualización del cliente
        when(clienteService.actualizarCliente(clienteId, clienteDetalles)).thenReturn(clienteDetalles);

        // Act
        ResponseEntity<Cliente> response = clienteController.actualizarCliente(clienteId, clienteDetalles);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Javier Hernandez", response.getBody().getNombre());
        assertEquals("javier.hernandez@example.com", response.getBody().getEmail());
        assertEquals("173-106-4388", response.getBody().getTelefono());
        assertEquals("Av. 5 de mayo S/n", response.getBody().getDireccion());
        assertEquals(LocalDate.of(1985,10,15), response.getBody().getFechaNacimiento());
        assertEquals(fechaRegistro, response.getBody().getFechaRegistro());
        assertEquals(true, response.getBody().getActivo());

        // Verificar que se hayan llamado los métodos correctos
        verify(clienteService).obtenerClientePorId(clienteId);
        verify(clienteService).actualizarCliente(clienteId, clienteDetalles);
    }

    @Test
    void testEliminarCliente() {
        // Arrange
        Long clienteId = 1L;

        // Act
        ResponseEntity<Void> response = clienteController.eliminarCliente(clienteId);

        // Assert
        assertEquals(204, response.getStatusCode().value());
        verify(clienteService).eliminarCliente(clienteId);
    }

}
