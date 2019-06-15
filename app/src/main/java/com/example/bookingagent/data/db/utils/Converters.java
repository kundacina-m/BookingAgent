package com.example.bookingagent.data.db.utils;

import androidx.room.TypeConverter;
import com.example.bookingagent.data.db.entities.Comment;
import com.example.bookingagent.data.db.entities.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static String fromServiceArrayList(ArrayList<Service> services) {
        Type type = new TypeToken<ArrayList<Service>>() {
        }.getType();
        return new Gson().toJson(services, type);
    }

    @TypeConverter
    public static String fromCommentArrayList(ArrayList<Comment> comments) {
        Type type = new TypeToken<ArrayList<Comment>>() {
        }.getType();
        return new Gson().toJson(comments, type);
    }

    @TypeConverter
    public static String fromStringArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Service> toServiceArrayList(String services) {
        Type type = new TypeToken<ArrayList<Service>>() {
        }.getType();
        return new Gson().fromJson(services, type);
    }

    @TypeConverter
    public static ArrayList<Comment> toCommentArrayList(String comments) {
        Type type = new TypeToken<ArrayList<Comment>>() {
        }.getType();
        return new Gson().fromJson(comments, type);
    }

    @TypeConverter
    public static ArrayList<String> toStringArrayList(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }




}
