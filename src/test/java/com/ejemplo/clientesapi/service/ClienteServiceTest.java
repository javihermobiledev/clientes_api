package com.ejemplo.clientesapi.service;

import com.ejemplo.clientesapi.model.Cliente;
import com.ejemplo.clientesapi.exception.ClienteNotFoundException;
import com.ejemplo.clientesapi.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("test")
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
    void testObtenerClientePorId() {
        // Arrange
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNombre("Alyssa Hernandez");
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = clienteService.obtenerClientePorId(clienteId);

        // Assert
        assertNotNull(result);
        assertEquals(clienteId, result.getId());
        assertEquals("Alyssa Hernandez", result.getNombre());
    }

    @Test
    void testObtenerClientePorIdNotFound() {
        // Arrange
        Long clienteId = 2L;
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ClienteNotFoundException.class, () -> clienteService.obtenerClientePorId(clienteId));
    }
    @Test
    void testGuardarCliente() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Isabel Silvestre");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Act
        Cliente result = clienteService.crearCliente(cliente);

        // Assert
        assertNotNull(result);
        assertEquals(cliente.getNombre(), result.getNombre());
    }
    @Test
    void testActualizarCliente() {
        // Arrange
        Long clienteId = 3L;
        Cliente clienteExistente = new Cliente(clienteId, "Juan Perez", "juan.perez@example.com","555-3452",
                "Calle Vieja 789", LocalDate.of(1980, 3, 20), LocalDateTime.now(), true);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));

        // Actualizar el cliente
        clienteExistente.setNombre("Jorge Hernandez");
        clienteExistente.setEmail("jorge.hernandez@example.com");
        clienteExistente.setTelefono("555-1234");
        clienteExistente.setDireccion("Los Alamos 321");
        clienteExistente.setFechaNacimiento(LocalDate.of(1990, 5, 15));
        clienteExistente.setFechaRegistro(LocalDateTime.now());
        clienteExistente.setActivo(true);
        when(clienteRepository.save(clienteExistente)).thenReturn(clienteExistente);

        // Act
        Cliente clienteActualizado = clienteService.actualizarCliente(clienteId, clienteExistente);

        // Assert
        assertNotNull(clienteActualizado);
        assertEquals("Jorge Hernandez", clienteActualizado.getNombre());
        assertEquals("jorge.hernandez@example.com", clienteActualizado.getEmail());
        assertEquals("555-1234", clienteActualizado.getTelefono());
        assertEquals("Los Alamos 321", clienteActualizado.getDireccion());
        assertEquals(LocalDate.of(1990, 5, 15), clienteActualizado.getFechaNacimiento());  // Verificar fecha de nacimiento

        assertNotNull(clienteActualizado.getFechaRegistro());
        assertTrue(clienteActualizado.getActivo());
    }

    @Test
    void testEliminarCliente() {
        // Arrange
        Long clienteId = 4L;
        Cliente clienteExistente = new Cliente(clienteId, "Maria Garcia", "maria.garcia@example.com",
                "555-9876", "Calle Nueva 123", LocalDate.of(1985, 8, 20), LocalDateTime.now(), true);

        // Simular la búsqueda de un cliente existente
        when(clienteRepository.existsById(clienteId)).thenReturn(true);
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));

        // Act
        clienteService.eliminarCliente(clienteId);

        // Assert
        verify(clienteRepository).deleteById(clienteId);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        assertThrows(ClienteNotFoundException.class, () -> {
            clienteService.obtenerClientePorId(clienteId);
        });

        Optional<Cliente> clienteBuscado = clienteRepository.findById(clienteId);
        assertTrue(clienteBuscado.isEmpty(), "Cliente nulo después de la eliminación.");
    }

}

