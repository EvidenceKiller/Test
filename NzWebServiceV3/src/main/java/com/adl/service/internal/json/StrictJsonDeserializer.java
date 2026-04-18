package com.adl.service.internal.json;

import com.adl.web.service.BuildConfig;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

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

        if (!BuildConfig.ENABLE_STRICT_JSON_DESERIALIZATION) {
            return ReflectiveJsonMapper.fromJsonObject(jsonObject, typeOfT, context);
        }

        StrictJsonFieldValidator.validate(jsonObject, typeOfT);
        return ReflectiveJsonMapper.fromJsonObject(jsonObject, typeOfT, context);
    }
}
