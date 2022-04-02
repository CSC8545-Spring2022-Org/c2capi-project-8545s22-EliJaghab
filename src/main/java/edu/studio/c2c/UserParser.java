package edu.studio.c2c;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class UserParser {

    private final Gson gson;

    public UserParser() {
        this.gson = new GsonBuilder().create();
    }

    public List<User> parseUsers(String jsonContent) {
        List<User> users = new ArrayList<User>();
        if (jsonContent != null && !jsonContent.isEmpty()) {
            Type collectionType = new TypeToken<List<User>>() {
            }.getType();
            users = gson.fromJson(jsonContent, collectionType);
        }
        return users;
    }

}