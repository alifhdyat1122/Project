package com.example.responsi.service;

import com.example.responsi.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Repository {
    @GET("v2/top-headlines?country=us&apiKey=d2890b74ad774387bbf8258f8996075d")
    Call<NewsResponse> getDiscover(@Query("api_key") String api_key,
                                   @Query("country") String country,
                                   @Query("year") String year);
}
