package com.adl.service.internal;

import com.adl.web.service.BuildConfig;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 严格校验 JSON 字段与 Java 字段（含 {@link SerializedName}）一致后再反序列化。
 * <p>
 * 对<strong>根类型</strong>不能调用 {@code context.deserialize(json, typeOfT)}：
 * Gson 会再次进入本 {@link JsonDeserializer}（{@link com.google.gson.internal.bind.TreeTypeAdapter}），
 * 形成无限递归直至 {@link StackOverflowError}。根对象应实例化后按字段 {@code context.deserialize}。
 */
public final class StrictJsonDeserializer<T> implements JsonDeserializer<T> {
    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            throw new JsonParseException("is not json object : " + json.getClass().getSimpleName());
        }

        JsonObject jsonObject = json.getAsJsonObject();
        Class<?> rawType = getRawType(typeOfT);

        if (!BuildConfig.ENABLE_STRICT_JSON_DESERIALIZATION) {
            // 不做严格校验
            return deserializeReflective(jsonObject, typeOfT, context);
        }

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

        return deserializeReflective(jsonObject, typeOfT, context);
    }

    @SuppressWarnings("unchecked")
    private static <T> T deserializeReflective(
            JsonObject jsonObject, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Class<?> rawType = getRawType(typeOfT);
        T instance = (T) createInstance(rawType);
        populateFields(instance, rawType, jsonObject, context);
        return instance;
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

    private static void populateFields(
            Object instance,
            Class<?> rawType,
            JsonObject jsonObject,
            JsonDeserializationContext context) throws JsonParseException {
        for (Class<?> c = rawType; c != null && c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                if (shouldSkipField(field)) {
                    continue;
                }
                String jsonFieldName = getSerializedName(field);
                JsonElement el = jsonObject.get(jsonFieldName);
                try {
                    field.setAccessible(true);
                    Object value = context.deserialize(el, field.getGenericType());
                    field.set(instance, value);
                } catch (IllegalAccessException e) {
                    throw new JsonParseException("无法写入字段: " + field.getName(), e);
                }
            }
        }
    }

    private static boolean shouldSkipField(Field field) {
        int mod = field.getModifiers();
        if (Modifier.isStatic(mod)) {
            return true;
        }
        if (Modifier.isTransient(mod)) {
            return true;
        }
        return field.isSynthetic();
    }

    private static Object createInstance(Class<?> rawType) throws JsonParseException {
        try {
            Constructor<?> ctor = rawType.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (Exception e) {
            throw new JsonParseException("无法实例化: " + rawType.getName(), e);
        }
    }

    private static String getSerializedName(Field field) {
        SerializedName annotation = field.getAnnotation(SerializedName.class);
        return annotation != null ? annotation.value() : field.getName();
    }

    private static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) type).getRawType();
        }
        throw new IllegalArgumentException("不支持的类型: " + type);
    }
}
