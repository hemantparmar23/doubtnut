package com.example.doubnut.data.network;

import com.example.doubnut.data.network.response.NewsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    String API_KEY = "58b667f22e78447eb16fa3dfb0173aa9";

    @GET("v2/top-headlines?apiKey=" + API_KEY)
    Single<NewsResponse> getNewsList(@Query("country") String country);

//    @GET("repos/{owner}/{name}")
//    Single<Repo> getRepo(@Path("owner") String owner, @Path("name") String name);
}
