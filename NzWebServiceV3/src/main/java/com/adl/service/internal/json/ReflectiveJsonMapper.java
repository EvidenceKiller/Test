package com.adl.service.internal.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

/**
 * 仅负责对象反序列化映射，不做字段一致性校验。
 */
final class ReflectiveJsonMapper {

    private ReflectiveJsonMapper() {
    }

    @SuppressWarnings("unchecked")
    static <T> T fromJsonObject(JsonObject jsonObject, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Class<?> rawType = StrictJsonFieldValidator.getRawType(typeOfT);
        T instance = (T) createInstance(rawType);
        Map<TypeVariable<?>, Type> typeVarMap = buildTypeVarMap(typeOfT, rawType);
        populateFields(instance, rawType, jsonObject, context, typeVarMap);
        return instance;
    }

    private static void populateFields(
            Object instance,
            Class<?> rawType,
            JsonObject jsonObject,
            JsonDeserializationContext context,
            Map<TypeVariable<?>, Type> typeVarMap) throws JsonParseException {
        for (Class<?> c = rawType; c != null && c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                if (StrictJsonFieldValidator.shouldSkipField(field)) {
                    continue;
                }
                String jsonFieldName = StrictJsonFieldValidator.getSerializedName(field);
                JsonElement el = jsonObject.get(jsonFieldName);
                try {
                    field.setAccessible(true);
                    Type resolvedFieldType = resolveType(field.getGenericType(), typeVarMap);
                    Object value = context.deserialize(el, resolvedFieldType);
                    field.set(instance, value);
                } catch (IllegalAccessException e) {
                    throw new JsonParseException("无法写入字段: " + field.getName(), e);
                }
            }
        }
    }

    private static Map<TypeVariable<?>, Type> buildTypeVarMap(Type typeOfT, Class<?> rawType) {
        Map<TypeVariable<?>, Type> map = new HashMap<>();
        if (!(typeOfT instanceof ParameterizedType)) {
            return map;
        }
        ParameterizedType parameterizedType = (ParameterizedType) typeOfT;
        TypeVariable<?>[] vars = rawType.getTypeParameters();
        Type[] actualArgs = parameterizedType.getActualTypeArguments();
        int len = Math.min(vars.length, actualArgs.length);
        for (int i = 0; i < len; i++) {
            map.put(vars[i], actualArgs[i]);
        }
        return map;
    }

    private static Type resolveType(Type originType, Map<TypeVariable<?>, Type> typeVarMap) {
        if (originType instanceof TypeVariable) {
            Type resolved = typeVarMap.get(originType);
            return resolved == null ? originType : resolved;
        }
        if (originType instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType) originType;
            Type ownerType = p.getOwnerType();
            Type resolvedOwner = ownerType == null ? null : resolveType(ownerType, typeVarMap);
            Type rawType = p.getRawType();
            Type[] args = p.getActualTypeArguments();
            Type[] resolvedArgs = new Type[args.length];
            for (int i = 0; i < args.length; i++) {
                resolvedArgs[i] = resolveType(args[i], typeVarMap);
            }
            return new ResolvedParameterizedType(resolvedOwner, rawType, resolvedArgs);
        }
        if (originType instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) originType;
            Type resolvedComponentType = resolveType(genericArrayType.getGenericComponentType(), typeVarMap);
            return new ResolvedGenericArrayType(resolvedComponentType);
        }
        if (originType instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) originType;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            Type[] resolvedLower = new Type[lowerBounds.length];
            Type[] resolvedUpper = new Type[upperBounds.length];
            for (int i = 0; i < lowerBounds.length; i++) {
                resolvedLower[i] = resolveType(lowerBounds[i], typeVarMap);
            }
            for (int i = 0; i < upperBounds.length; i++) {
                resolvedUpper[i] = resolveType(upperBounds[i], typeVarMap);
            }
            return new ResolvedWildcardType(resolvedUpper, resolvedLower);
        }
        return originType;
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

    private static final class ResolvedParameterizedType implements ParameterizedType {
        private final Type ownerType;
        private final Type rawType;
        private final Type[] actualTypeArguments;

        private ResolvedParameterizedType(Type ownerType, Type rawType, Type[] actualTypeArguments) {
            this.ownerType = ownerType;
            this.rawType = rawType;
            this.actualTypeArguments = actualTypeArguments.clone();
        }

        @Override
        public Type[] getActualTypeArguments() {
            return actualTypeArguments.clone();
        }

        @Override
        public Type getRawType() {
            return rawType;
        }

        @Override
        public Type getOwnerType() {
            return ownerType;
        }
    }

    private static final class ResolvedGenericArrayType implements GenericArrayType {
        private final Type genericComponentType;

        private ResolvedGenericArrayType(Type genericComponentType) {
            this.genericComponentType = genericComponentType;
        }

        @Override
        public Type getGenericComponentType() {
            return genericComponentType;
        }
    }

    private static final class ResolvedWildcardType implements WildcardType {
        private final Type[] upperBounds;
        private final Type[] lowerBounds;

        private ResolvedWildcardType(Type[] upperBounds, Type[] lowerBounds) {
            this.upperBounds = upperBounds.clone();
            this.lowerBounds = lowerBounds.clone();
        }

        @Override
        public Type[] getUpperBounds() {
            return upperBounds.clone();
        }

        @Override
        public Type[] getLowerBounds() {
            return lowerBounds.clone();
        }
    }
}

