package com.example.dizzer.gittesttask.presenter;


import android.content.Context;
import android.util.Log;

import com.example.dizzer.gittesttask.model.IRepositoriesList;
import com.example.dizzer.gittesttask.model.Repositories;
import com.example.dizzer.gittesttask.model.RepositoriesList;
import com.example.dizzer.gittesttask.view.IRepositoriesListFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class RepositoriesListPresenter implements IRepositoriesListPresenter {

    private Context context;
    private IRepositoriesList repositoriesList;
    private IRepositoriesListFragment repositoriesListFragment;
    private Call<List<Repositories>> result;
    private List<Repositories> repositories;

    public RepositoriesListPresenter(Context context, IRepositoriesListFragment repositoriesListFragment) {
        this.context = context;
        this.repositoriesListFragment = repositoriesListFragment;
        repositoriesList = new RepositoriesList();
    }

    @Override
    public void getRepositories(String name) {
        result = repositoriesList.getRepositories(name);
        result.enqueue(new Callback<List<Repositories>>() {
            @Override
            public void onResponse(Call<List<Repositories>> call, Response<List<Repositories>> response) {
                Log.d("Log.d", new Gson().toJson(response));
                repositories = new ArrayList<>();
                if (response.body()!=null) {
                    repositories.addAll(response.body());
                    repositoriesListFragment.showRepositories(repositories);
                }
            }

            @Override
            public void onFailure(Call<List<Repositories>> call, Throwable t) {
                try {
                    throw new Exception("Connection failed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
