package com.example.aplicacindeprueba.io;

import com.example.aplicacindeprueba.io.model.UserRequest;
import com.example.aplicacindeprueba.io.response.LogingResponse;
import com.google.gson.annotations.JsonAdapter;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("register")
    Call<LogingResponse> login(@Body UserRequest userRequest);
   /*@POST("register")
   Call<LogingResponse> login(@FieldMap Map<String, String> map);*/

}
