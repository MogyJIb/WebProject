package by.gstu.training.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static String toJson(Object obj){
        return gson.toJson(obj);
    }
    public static Object fromJson(String jsString,Class objClass){
        return gson.fromJson(jsString,objClass);
    }
}