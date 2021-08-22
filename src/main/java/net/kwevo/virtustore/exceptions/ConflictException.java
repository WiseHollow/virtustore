package net.kwevo.virtustore.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends HttpException {
    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
