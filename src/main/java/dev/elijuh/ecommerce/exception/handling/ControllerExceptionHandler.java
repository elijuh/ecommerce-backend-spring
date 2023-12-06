package dev.elijuh.ecommerce.exception.handling;

import dev.elijuh.ecommerce.exception.DuplicateEntryException;
import dev.elijuh.ecommerce.exception.InvalidCredentialsException;
import dev.elijuh.ecommerce.exception.ResourceNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author elijuh
 */

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        if (e instanceof ResourceNotFoundException) {
            return new ResponseEntity<>(new ErrorResponse(e), HttpStatus.NOT_FOUND);
        } else if (e instanceof InternalAuthenticationServiceException ex && ex.getCause() instanceof Exception cause) {
            ResponseStatus status = cause.getClass().getAnnotation(ResponseStatus.class);
            return new ResponseEntity<>(new ErrorResponse(cause), status == null ? HttpStatus.INTERNAL_SERVER_ERROR : status.value());
        } else if (e instanceof HttpMediaTypeNotSupportedException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex), HttpStatus.BAD_REQUEST);
        } else if (e instanceof BadCredentialsException) {
            return new ResponseEntity<>(new ErrorResponse(new InvalidCredentialsException()), HttpStatus.UNAUTHORIZED);
        } else if (e instanceof ExpiredJwtException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex), HttpStatus.UNAUTHORIZED);
        } else if (e instanceof DuplicateEntryException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex)
                .withProperty("value", ex.getDuplicatedValue())
                .withProperty("valueName", ex.getValueName()),
                HttpStatus.CONFLICT
            );
        } else if (e instanceof MethodArgumentNotValidException ex) {
            ErrorResponse body = new ErrorResponse("INVALID_REQUEST_ARGUMENTS", "requesty body validation failed");
            List<String> errors = new ArrayList<>();
            for (FieldError error : ex.getFieldErrors()) {
                errors.add(error.getField() + " " + error.getDefaultMessage());
            }
            body.withProperty("errors", errors);
            return ResponseEntity.badRequest().body(body);
        } else if (e instanceof HttpMessageNotReadableException ex) {
            ErrorResponse body = new ErrorResponse("BAD_REQUEST", "failed to read http request message");
            if (ex.getMessage().startsWith("Required request body is missing")) {
                body.withProperty("reason", "Required request body is missing");
            }
            return ResponseEntity.badRequest().body(body);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(new ErrorResponse(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ErrorResponse(
            "UNEXPECTED_ERROR",
            "Unknown error occured, please contact an administrator for help."
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
