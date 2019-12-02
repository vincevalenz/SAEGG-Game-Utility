package com.example.reviewer.Models;

import com.google.gson.annotations.SerializedName;

public class ObjectModelgetSelfInfo {

    @SerializedName("unique_id")
    private String unique_id;

    @SerializedName("name")
    private String name;

    public ObjectModelgetSelfInfo(){}

    public String getUnique_id() { return unique_id; }

    public String getName() { return name; }
}