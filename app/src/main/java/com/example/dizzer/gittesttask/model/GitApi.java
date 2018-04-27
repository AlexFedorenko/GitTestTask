package com.example.dizzer.gittesttask.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Dizzer on 4/27/2018.
 */

public interface GitApi {

    @GET("/orgs/{organization}")
    Call<Organization> searchUser(@Path("organization") String organization);

    @GET("/users/{user}/repos")
    Call<List<Repositories>> getRepos(@Path("user") String user);
}
