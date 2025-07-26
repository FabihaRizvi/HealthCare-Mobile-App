package com.academics.hospitalmanagementsystem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIInterface {
    @GET("v2/top-headlines")
    Call<NewsResponse> getHealthArticles(
            @Query("category") String category,
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
