package com.ejemplo.clientesapi.exception;

public class ClienteAlreadyExistsException extends RuntimeException {

    private final Long id;

    public ClienteAlreadyExistsException(Long id) {
        super("Ya existe un cliente con ID: " + id);
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
