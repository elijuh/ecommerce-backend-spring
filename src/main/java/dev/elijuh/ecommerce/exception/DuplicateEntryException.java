package dev.elijuh.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author elijuh
 */

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntryException extends RuntimeException {

    private final String valueName;
    private final Object duplicatedValue;

    public DuplicateEntryException(String valueName, Object duplicatedValue) {
        super("Entry already exists with same unique value");
        this.valueName = valueName;
        this.duplicatedValue = duplicatedValue;
    }
}
