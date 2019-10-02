package com.android.demo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void additionIsCorrect() throws Exception {

        Gson gson = new Gson();
        List<Bean> list = new ArrayList<>();
        list.add(new Bean("123"));
        Result<Bean> src = new Result<>(list);
        String json = gson.toJson(src);
        System.out.println(json);


        Result<Bean> result = parseTest(json, Bean.class);
        System.out.println(result.data.get(0).name);
    }

    public <T> Result<T> parseTest(String json, Class<T> cls){
        Result<T> result = new Result<>();
        List<T> list = new ArrayList<>();
        result.data = list;

        Gson gson = new Gson();
        Result<JsonElement> parseResult = gson.fromJson(json, new TypeToken<Result<JsonElement>>() {
        }.getType());

        List<JsonElement> elementList = parseResult.data;
        for(JsonElement element : elementList){
            T t = gson.fromJson(element.toString(), cls);
            list.add(t);
        }
        return result;
    }

    static class Result<T>{
        List<T> data;

        public Result(List<T> data) {
            this.data = data;
        }

        public Result() {
        }
    }

    static class Bean{
        String name;

        public Bean(String name) {
            this.name = name;
        }

        public Bean() {
        }
    }
}