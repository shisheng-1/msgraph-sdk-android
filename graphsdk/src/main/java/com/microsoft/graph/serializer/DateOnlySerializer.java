package com.microsoft.graph.serializer;

import com.microsoft.graph.model.DateOnly;

public class DateOnlySerializer {

    public static DateOnly deserialize(String strVal) {
        return new DateOnly(strVal);
    }

    public static String serialize(DateOnly src) {
        return src.toString();
    }
}
