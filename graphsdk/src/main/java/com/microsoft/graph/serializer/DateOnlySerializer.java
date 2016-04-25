package com.microsoft.graph.serializer;

import com.microsoft.graph.model.DateOnly;

import java.text.ParseException;

public class DateOnlySerializer {

    public static DateOnly deserialize(String strVal) throws ParseException {
        return DateOnly.parse(strVal);
    }

    public static String serialize(DateOnly src) {
        return src.toString();
    }
}
