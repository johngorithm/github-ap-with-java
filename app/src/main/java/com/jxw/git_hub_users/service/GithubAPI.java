package com.jxw.git_hub_users.service;

import com.jxw.git_hub_users.model.GithubUsersResponse;
import com.jxw.git_hub_users.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubAPI {
    @GET("/search/users?q=+language:java+location:nairobi&per_page=100")
    Call<GithubUsersResponse> getUsers();

    @GET("/users/{username}?client_id=694ce0aafdfbc47ad583&client_secret=58709f1741ce72e8102a05b41412b38750bf1cd0")
    Call<UserProfile> fetchProfile(@Path("username") String userName);
}
