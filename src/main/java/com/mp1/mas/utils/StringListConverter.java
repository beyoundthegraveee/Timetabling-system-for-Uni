package com.mp1.mas.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.lang.reflect.Type;
import java.util.List;


@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final Gson gson = new Gson();
    private static final Type listType = new TypeToken<List<String>>() {}.getType();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return (attribute == null || attribute.isEmpty()) ? "[]" : gson.toJson(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return (dbData == null || dbData.isBlank()) ? List.of() : gson.fromJson(dbData, listType);
    }
}
