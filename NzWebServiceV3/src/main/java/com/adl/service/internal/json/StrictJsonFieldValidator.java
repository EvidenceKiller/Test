package com.adl.service.internal.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 仅负责 JSON 字段与 Java 字段定义的一致性校验。
 */
final class StrictJsonFieldValidator {

    private StrictJsonFieldValidator() {
    }

    static void validate(JsonObject jsonObject, Type typeOfT) throws JsonParseException {
        Class<?> rawType = getRawType(typeOfT);
        Set<String> jsonFields = new HashSet<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            jsonFields.add(entry.getKey());
        }

        Set<String> javaFields = new HashSet<>();
        collectJavaFieldNames(rawType, javaFields);

        Set<String> extraJsonFields = new HashSet<>(jsonFields);
        extraJsonFields.removeAll(javaFields);

        Set<String> missingJsonFields = new HashSet<>(javaFields);
        missingJsonFields.removeAll(jsonFields);

        if (!extraJsonFields.isEmpty() || !missingJsonFields.isEmpty()) {
            StringBuilder error = new StringBuilder("字段不匹配: ")
                    .append(rawType.getSimpleName())
                    .append(" - ");

            if (!extraJsonFields.isEmpty()) {
                error.append("JSON 包含 Java 类中不存在的字段: ").append(extraJsonFields).append("; ");
            }
            if (!missingJsonFields.isEmpty()) {
                error.append("Java 类包含 JSON 中不存在的字段: ").append(missingJsonFields);
            }
            throw new JsonParseException(error.toString());
        }
    }

    private static void collectJavaFieldNames(Class<?> rawType, Set<String> javaFields) {
        for (Class<?> c = rawType; c != null && c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                if (shouldSkipField(field)) {
                    continue;
                }
                javaFields.add(getSerializedName(field));
            }
        }
    }

    static boolean shouldSkipField(Field field) {
        int mod = field.getModifiers();
        if (Modifier.isStatic(mod)) {
            return true;
        }
        if (Modifier.isTransient(mod)) {
            return true;
        }
        return field.isSynthetic();
    }

    static String getSerializedName(Field field) {
        SerializedName annotation = field.getAnnotation(SerializedName.class);
        return annotation != null ? annotation.value() : field.getName();
    }

    static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) type).getRawType();
        }
        throw new IllegalArgumentException("不支持的类型: " + type);
    }
}

