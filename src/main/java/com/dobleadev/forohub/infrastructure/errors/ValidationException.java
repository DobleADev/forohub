package com.dobleadev.forohub.infrastructure.errors;

public class ValidationException extends RuntimeException {
    public ValidationException(String mensaje) {
        super(mensaje);
    }
}

