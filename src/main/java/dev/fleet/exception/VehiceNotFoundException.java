package dev.fleet.exception;

public class VehiceNotFoundException extends RuntimeException {
    public VehiceNotFoundException(Long id) {
        super("Vehicle with id " + id + " not found");
    }
}
