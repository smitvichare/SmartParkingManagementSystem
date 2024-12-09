package com.smartparkingms.exception;

public class PlateNotFoundException extends RuntimeException {
    public PlateNotFoundException(String message) {
        super(message);
    }
}
