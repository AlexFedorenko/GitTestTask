package com.example.dizzer.gittesttask.model;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Dizzer on 4/27/2018.
 */

public interface IRepositoriesList {
    Call<List<Repositories>> getRepositories(String organization);
}
