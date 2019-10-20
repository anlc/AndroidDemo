package com.android.demo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.junit.Test;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-10-15
 */
public class GsonTest {

    class Bean {
        String name;
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
    public void TestGson(){
        System.out.println(new Gson().toJson(new Bean("name", "age")));
    }

}
