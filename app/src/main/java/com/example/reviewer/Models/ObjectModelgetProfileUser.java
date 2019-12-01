package com.example.reviewer.Models;

import com.google.gson.annotations.SerializedName;

public class ObjectModelgetProfileUser {

    @SerializedName("name")
    private String name;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    public ObjectModelgetProfileUser(){}

    public String getUser_name() { return name; }

    public String getCreated_at() { return created_at; }

    public String getUpdated_at() { return updated_at; }
}
