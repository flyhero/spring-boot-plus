package io.github.flyhero.springboot.plus.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER;
    private final static ObjectMapper OBJECT_MAPPER_REFLECT;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER_REFLECT = new ObjectMapper();
        OBJECT_MAPPER_REFLECT.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    private JsonUtil() {
    }

    public static <T> T json2Object(String strBody, Class<T> c) throws IOException {
        if (strBody == null || "".equals(strBody)) {
            return null;
        } else {
            return OBJECT_MAPPER.readValue(strBody, c);
        }
    }

    public static String object2Json(Object o) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(o);
    }

    public static <T> List<T> json2List(String strBody, Class<T> c) throws IOException {
        return (List<T>) json2Parametrized(strBody, ArrayList.class, c);
    }

    /**
     * Str -> 参数化类型
     *
     * @param strBody           Json格式的字符串
     * @param parametrizedClass 参数化类型: List.class
     * @param params            参数化类型里的参数: String
     * @return List<String>
     */
    public static <T> T json2Parametrized(String strBody, Class<T> parametrizedClass, Class<?>... params) throws JsonProcessingException {
        if (strBody == null || "".equals(strBody)) {
            return null;
        } else {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(parametrizedClass, params);
            return OBJECT_MAPPER.readValue(strBody, javaType);
        }
    }

    public static <T> T json2ObjectByReflect(String json, Class<T> zlass) throws JsonProcessingException {
        return OBJECT_MAPPER_REFLECT.readValue(json, zlass);
    }

    public static <T> String object2JsonByReflect(T obj) throws JsonProcessingException {
        return OBJECT_MAPPER_REFLECT.writeValueAsString(obj);
    }
}
