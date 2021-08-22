package net.kwevo.virtustore.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidStoreCategoryException extends HttpException {
    public InvalidStoreCategoryException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
