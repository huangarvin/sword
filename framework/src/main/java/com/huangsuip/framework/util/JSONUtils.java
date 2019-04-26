package com.huangsuip.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author HuangSuip
 */
public class JSONUtils {

    protected static final ObjectMapper OBJECT_MAPPER = new UnifiedObjectMapper();

    public static String toJSONString(Object object) {

        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 序列化异常", e);
        }
    }

    public static byte[] toBytes(Object value) {
        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                OBJECT_MAPPER.writeValue(outputStream, value);
                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("JSON 序列化异常", e);
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(byte[] data, Class<T> clazz) {
        try {
            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
                return OBJECT_MAPPER.readValue(inputStream, clazz);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseArray(String json, Class<T> t) {
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<T>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(File file, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
