package dev.elijuh.ecommerce.exception.handling;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.elijuh.ecommerce.utils.StringUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author elijuh
 */

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private final String code;
    private final String message;

    private final Map<String, Object> properties = new HashMap<>();

    public ErrorResponse(Exception e) {
        code = StringUtil.formatErrorCode(e.getClass().getName());
        message = e.getMessage();
    }

    public ErrorResponse withProperty(String name, Object value) {
        this.properties.put(name, value);
        return this;
    }
}
