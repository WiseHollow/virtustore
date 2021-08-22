package net.kwevo.virtustore.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "You are unauthorized.");
    }
}
