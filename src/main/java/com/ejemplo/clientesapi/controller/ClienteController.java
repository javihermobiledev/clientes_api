package com.ejemplo.clientesapi.controller;

import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.clientesapi.model.Cliente;
import com.ejemplo.clientesapi.service.ClienteService;

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
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok("Cliente eliminado con exito");
    }
}



