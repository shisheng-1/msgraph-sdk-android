package com.microsoft.graph.serializer;

import com.microsoft.graph.model.Date;

public class DateSerializer {

    public static Date deserialize(String strVal) {
        return new Date(strVal);
    }

    public static String serialize(Date src) {
        return src.toString();
    }
}
