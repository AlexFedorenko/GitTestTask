package com.example.dizzer.gittesttask.model;

import retrofit2.Call;

/**
 * Created by Dizzer on 4/27/2018.
 */

public interface ISearchResult {
    Call<Organization> searchData(String name);
}
