package net.kwevo.virtustore.exceptions.advice;

import net.kwevo.virtustore.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StoreControllerAdvice {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorMessage> handleHttpException(HttpException httpException) {
        ErrorMessage errorMessage = new ErrorMessage(
                httpException.getMessage(),
                "TODO",
                "TODO",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorMessage, httpException.getHttpStatus());
    }
}
