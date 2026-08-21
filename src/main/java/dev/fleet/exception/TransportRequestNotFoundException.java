package dev.fleet.exception;

public class TransportRequestNotFoundException extends RuntimeException {

    public TransportRequestNotFoundException(Long id) {
        super("Transport request with id " + id + " not found");
    }
}
