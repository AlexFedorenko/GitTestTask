package com.example.dizzer.gittesttask.model;

import com.example.dizzer.gittesttask.util.App;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class RepositoriesList implements IRepositoriesList {
    @Override
    public Call<List<Repositories>> getRepositories(String organization) {
        return App.getApi().getRepos(organization);
    }
}
