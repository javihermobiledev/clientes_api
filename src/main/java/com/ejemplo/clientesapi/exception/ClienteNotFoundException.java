package com.ejemplo.clientesapi.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("No se encontró el cliente con ID: " + id);
    }
}
