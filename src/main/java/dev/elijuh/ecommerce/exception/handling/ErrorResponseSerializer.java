package dev.elijuh.ecommerce.exception.handling;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Map;

/**
 * @author elijuh
 */

@JsonComponent
public class ErrorResponseSerializer extends JsonSerializer<ErrorResponse> {
    @Override
    public void serialize(ErrorResponse errorResponse,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("code", errorResponse.getCode());
        if (errorResponse.getMessage() != null) {
            jsonGenerator.writeStringField("message", errorResponse.getMessage());
        }
        Map<String, Object> properties = errorResponse.getProperties();
        for (String name : properties.keySet()) {
            jsonGenerator.writeObjectField(name, properties.get(name));
        }
        jsonGenerator.writeEndObject();
    }
}
