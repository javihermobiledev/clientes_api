package com.ejemplo.clientesapi.exception;

public class ClienteNotFoundException extends RuntimeException {
    private final Long id;

    public ClienteNotFoundException(Long id) {
        super("No se encontró el cliente con ID: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
