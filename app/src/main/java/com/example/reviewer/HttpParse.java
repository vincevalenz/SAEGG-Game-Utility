package com.example.reviewer;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;

public class HttpParse {

    String FinalHttpData = "";
    String Result;
    BufferedWriter bufferedWriter;
    OutputStream outputStream;
    BufferedWriter bufferedReader;
    StringBuilder stringBuilder = new StringBuilder();
    URL url;

    public String postRequest(HashMap<String, String> Data, String UttpUrlHolder) {

        return FinalHttpData;
    }

    public String FinalDataParse(HashMap<String, String> hashMap2) throws UnsupportedEncodingException {

        Result = stringBuilder.toString();
        return Result;
    }
}
