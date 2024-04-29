package com.litografiaartesplanchas.servicesservice.utils.errors;

/**
 * The class `NotFoundException` extends `Exception` and provides a custom message for not found
 * exceptions.
 */
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}