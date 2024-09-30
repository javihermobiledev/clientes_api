package com.ejemplo.clientesapi.controller;

import com.ejemplo.clientesapi.model.Cliente;
import com.ejemplo.clientesapi.service.ClienteService;
import com.ejemplo.clientesapi.exception.ClienteNotFoundException; // Asegúrate de importar la excepción
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();  // Devuelve 404 si no encuentra el cliente
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente clienteGuardado = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalles) {
        // Obtén el cliente existente
        Cliente clienteExistente = clienteService.obtenerClientePorId(id);

        // Actualiza los detalles del cliente existente
        clienteExistente.setNombre(clienteDetalles.getNombre());
        clienteExistente.setEmail(clienteDetalles.getEmail());
        clienteExistente.setTelefono(clienteDetalles.getTelefono());
        clienteExistente.setDireccion(clienteDetalles.getDireccion());
        clienteExistente.setFechaNacimiento(clienteDetalles.getFechaNacimiento());
        clienteExistente.setFechaRegistro(clienteDetalles.getFechaRegistro());
        clienteExistente.setActivo(clienteDetalles.getActivo());

        Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteExistente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}



