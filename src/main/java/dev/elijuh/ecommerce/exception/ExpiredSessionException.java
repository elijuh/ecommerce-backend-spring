package dev.elijuh.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author elijuh
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiredSessionException extends RuntimeException {

    public ExpiredSessionException() {
        super("expired authentication token provided");
    }
}
