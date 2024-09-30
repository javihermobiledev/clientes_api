package com.ejemplo.clientesapi.repository;

import com.ejemplo.clientesapi.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//@DataJpaTest
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void testObtenerClientePorId() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setEmail("juan.perez@example.com");
        cliente.setTelefono("555-1234");
        cliente.setDireccion("Calle Luna 456");
        cliente.setFechaNacimiento(LocalDate.of(1980, 5, 10));
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);

        cliente = clienteRepository.save(cliente);
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.getId());

        // Assert
        assertThat(clienteEncontrado).isPresent();
        assertEquals(cliente.getId(), clienteEncontrado.get().getId());
    }

    @Test
    void testBuscarClientePorEmail() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana López");
        cliente.setEmail("ana.lopez@example.com");
        cliente.setTelefono("555-7890");
        cliente.setDireccion("Calle Sur 789");
        cliente.setFechaNacimiento(LocalDate.of(1988, 10, 20));
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);

        cliente = clienteRepository.save(cliente);
        Optional<Cliente> clientePorEmail = clienteRepository.findByEmail(cliente.getEmail());

        // Assert
        assertThat(clientePorEmail).isPresent();
        assertEquals(cliente.getEmail(), clientePorEmail.get().getEmail());
    }

    @Test
    void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Isabel Silvestre");
        cliente.setEmail("isabel@example.com");
        cliente.setTelefono("555-5678");
        cliente.setDireccion("Avenida Siempre Viva 456");
        cliente.setFechaNacimiento(LocalDate.of(1990, 7, 22));
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);

        // Act
        Cliente crearCliente = clienteRepository.save(cliente);

        // Assert
        assertNotNull(crearCliente.getId());
        assertEquals(cliente.getNombre(), crearCliente.getNombre());
        assertEquals(cliente.getEmail(), crearCliente.getEmail());
        assertEquals(cliente.getTelefono(), crearCliente.getTelefono());
        assertEquals(cliente.getDireccion(), crearCliente.getDireccion());
        assertEquals(cliente.getFechaNacimiento(), crearCliente.getFechaNacimiento());
        assertEquals(cliente.getFechaRegistro(), crearCliente.getFechaRegistro());
        assertEquals(cliente.getActivo(), crearCliente.getActivo());
    }
    @Test
    void testActualizarCliente() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos Herrera");
        cliente.setEmail("carlos.herrera@example.com");
        cliente.setTelefono("555-4321");
        cliente.setDireccion("Avenida Sol 999");
        cliente.setFechaNacimiento(LocalDate.of(1992, 3, 14));
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);

        cliente = clienteRepository.save(cliente);

        cliente.setNombre("Carlos Alberto Herrera");
        cliente.setEmail("alberto.carlos@example.com");
        cliente.setTelefono("555-4321");
        cliente.setDireccion("Avenida Sol 1401");
        cliente.setFechaNacimiento(LocalDate.of(1985,10,15));
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);

        Cliente clienteActualizado = clienteRepository.save(cliente);

        // Assert
        assertEquals("Carlos Alberto Herrera", clienteActualizado.getNombre());
        assertEquals("alberto.carlos@example.com", clienteActualizado.getEmail());
        assertEquals("555-4321", clienteActualizado.getTelefono());
        assertEquals("Avenida Sol 1401", clienteActualizado.getDireccion());
        assertEquals(LocalDate.of(1985, 10, 15), clienteActualizado.getFechaNacimiento());

        // Verifica que la fecha de registro es reciente, en lugar de ser exacta
        assertTrue(clienteActualizado.getFechaRegistro().isAfter(LocalDateTime.now().minusSeconds(1)));
        assertTrue(clienteActualizado.getFechaRegistro().isBefore(LocalDateTime.now().plusSeconds(1)));

        assertEquals(true, clienteActualizado.getActivo());
    }


    @Test
    void testEliminarCliente() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Maria Garcia");
        cliente.setEmail("maria.garcia@example.com");
        cliente.setTelefono("555-9876");
        cliente.setDireccion("Calle Nueva 123");
        cliente.setFechaNacimiento(LocalDate.of(1985, 8, 20));
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);

        cliente = clienteRepository.save(cliente);

        // Act
        clienteRepository.delete(cliente);

        // Assert
        Optional<Cliente> clienteEliminado = clienteRepository.findById(cliente.getId());
        assertThat(clienteEliminado).isEmpty();
    }

    @Test
    void testEliminarClienteNoExistente() {
        // Assert
        Optional<Cliente> clienteEliminado = clienteRepository.findById(999L); // ID que no existe
        assertThat(clienteEliminado).isEmpty();
    }
}
