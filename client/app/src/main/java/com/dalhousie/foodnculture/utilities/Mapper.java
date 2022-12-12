package com.dalhousie.foodnculture.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

    public static String mapToJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public static <T> T mapFromJson(String json, Class<T> targetClass) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, targetClass);
    }
}
