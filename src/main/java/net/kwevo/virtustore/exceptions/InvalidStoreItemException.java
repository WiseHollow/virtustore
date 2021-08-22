package net.kwevo.virtustore.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidStoreItemException extends HttpException {
    public InvalidStoreItemException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
