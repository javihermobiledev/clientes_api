package com.ejemplo.clientesapi.exception;

public class ClienteAlreadyExistsException extends RuntimeException {
    public ClienteAlreadyExistsException(Long id) {
        super("Ya existe un cliente con ID: " + id);
    }
}
