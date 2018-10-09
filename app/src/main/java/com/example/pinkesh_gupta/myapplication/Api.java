package com.example.pinkesh_gupta.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://dl.dropboxusercontent.com/s/";

    @GET("2iodh4vg0eortkl/facts.json")
    Call<UserResponses> getUsers();

}
