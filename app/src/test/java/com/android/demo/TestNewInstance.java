package com.android.demo;

import java.lang.reflect.Array;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2020-01-08
 */
public class TestNewInstance {

    public static void main(String[] args) {
        String[] strings = emptyArray(String.class);
        System.out.println(strings.length);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] emptyArray(Class<T> kind) {
        return (T[]) Array.newInstance(kind, 0);
    }
}
