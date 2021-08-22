package net.kwevo.virtustore.exceptions.advice;

public record ErrorMessage(String message, String endpoint, String requestType, long timestamp) {

}
