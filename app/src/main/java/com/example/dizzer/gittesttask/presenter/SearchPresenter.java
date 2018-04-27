package com.example.dizzer.gittesttask.presenter;

import android.content.Context;
import android.util.Log;

import com.example.dizzer.gittesttask.model.IRepositoriesList;
import com.example.dizzer.gittesttask.model.ISearchResult;
import com.example.dizzer.gittesttask.model.Organization;
import com.example.dizzer.gittesttask.model.SearchResult;
import com.example.dizzer.gittesttask.view.ISearchFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class SearchPresenter implements ISearchPresenter {

    private Context context;
    private ISearchResult searchResult;
    private ISearchFragment searchFragment;
    private Call<Organization> result;
    private List<Organization> organizations;

    public SearchPresenter(Context context, ISearchFragment searchFragment) {
        this.context = context;
        this.searchFragment = searchFragment;
        this.searchResult = new SearchResult();
    }

    @Override
    public void getSearchResult(String name) {
        result = searchResult.searchData(name);
        result.enqueue(new Callback<Organization>() {
            @Override
            public void onResponse(Call<Organization> call, Response<Organization> response) {
                Log.d("Log.d", new Gson().toJson(response));
                organizations = new ArrayList<>();
                organizations.add(response.body());
                if (response.body() != null) searchFragment.showSearchResult(organizations);
            }

            @Override
            public void onFailure(Call<Organization> call, Throwable t) {
                try {
                    throw new Exception("Connection failed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
