package com.academics.hospitalmanagementsystem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIInterface {
    @GET("top-headlines")
    Call<NewsResponse> getHealthArticles(
            @Query("topic") String topic,
            @Query("lang") String lang,
            @Query("country") String country,
            @Query("token") String token
    );
}
