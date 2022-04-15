package com.example.uni.Models;

import java.lang.reflect.Field;

public class Models {

    public static Object getField(Object object , String fieldName) throws Exception {
        Field privateField = object.getClass().getDeclaredField(fieldName);
        privateField.setAccessible(true);
        return privateField.get(object);
    }

    public static void setField(Object object , String fieldName , Object value) throws Exception {
        Field privateField = object.getClass().getDeclaredField(fieldName);
        privateField.setAccessible(true);
        privateField.set(object,value);
    }

}
