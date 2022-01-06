package com.android.demo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.junit.Test;

/**
 * <p>
 *
 * @author anlc
 * @date 2019-10-15
 */
public class GsonTest {

    class Bean {

        transient String name;
        @SerializedName("age")
        String value;

        String test;

        public Bean() {
        }

        public Bean(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    @Test
    public void TestGson() {
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Gson gson = new Gson();
        System.out.println(gson.toJson(new Bean("name", "age")));
    }

}
