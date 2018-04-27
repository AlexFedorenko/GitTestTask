package com.example.dizzer.gittesttask.model;

import com.example.dizzer.gittesttask.util.App;

import retrofit2.Call;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class SearchResult implements  ISearchResult {
    @Override
    public Call<Organization> searchData(String name) {
        return App.getApi().searchUser(name);
    }
}
