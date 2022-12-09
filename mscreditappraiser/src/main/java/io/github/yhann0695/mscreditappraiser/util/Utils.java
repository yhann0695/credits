package io.github.yhann0695.mscreditappraiser.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {

    private Utils() {}

    public static String convertIntoJson(Object data) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(data);
    }
}
