package com.example.prash.xapodemo.engine;

import com.example.prash.xapodemo.pojo.MainPojo;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestInterface {

    @GET("repositories?sort=stars&order=desc&q=language:java language:kotlin&q=android created:>2018-11-26")
    Call<MainPojo> getGithubTrendingList(@Query("page") int page);

}
