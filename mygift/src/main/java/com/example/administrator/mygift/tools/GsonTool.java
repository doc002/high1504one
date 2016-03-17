package com.example.administrator.mygift.tools;

import com.google.gson.Gson;

/**
 * Created by Administrator on 16-3-16.
 */
public class GsonTool {
    private static Gson gson = new Gson();
    public static <T> T parseJson2Object(String json,Class<T> object){
        return gson.fromJson(json,object);
    }
}
