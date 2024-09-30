package com.ejemplo.clientesapi.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteAlreadyExistsExceptionTest {

    @Test
    void testClienteAlreadyExistsException() {
        Long id = 1L;
        String expectedMessage = "Ya existe un cliente con ID: " + id ;
        ClienteAlreadyExistsException exception = assertThrows(ClienteAlreadyExistsException.class, () -> {
            throw new ClienteAlreadyExistsException(id);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }
}
