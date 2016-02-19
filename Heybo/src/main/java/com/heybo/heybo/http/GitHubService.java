package com.heybo.heybo.http;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubService {
    @GET("/users/{user}")
    Call<ResponseBody> listRepos(@Path("user") String user);
}