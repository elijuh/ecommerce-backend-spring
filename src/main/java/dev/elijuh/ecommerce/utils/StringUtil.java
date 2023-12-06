package dev.elijuh.ecommerce.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author elijuh
 */

@UtilityClass
public class StringUtil {
    private final Pattern errorCodePattern = Pattern.compile("([A-Z][a-z]+)");

    public String formatErrorCode(String className) {
        Matcher matcher = errorCodePattern.matcher(className.replace("Exception", ""));
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            if (!builder.isEmpty()) {
                builder.append("_");
            }
            builder.append(matcher.group().toUpperCase());
        }
        return builder.toString();
    }

    public String readToken(String authHeader) {
        return authHeader != null && authHeader.startsWith("Bearer ")
            ? authHeader.substring(7)
            : null;
    }
}

