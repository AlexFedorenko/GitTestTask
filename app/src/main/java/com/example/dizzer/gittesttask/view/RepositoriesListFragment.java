package com.example.dizzer.gittesttask.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dizzer.gittesttask.R;
import com.example.dizzer.gittesttask.model.Repositories;
import com.example.dizzer.gittesttask.presenter.IRepositoriesListPresenter;
import com.example.dizzer.gittesttask.presenter.RepositoriesListAdapter;
import com.example.dizzer.gittesttask.presenter.RepositoriesListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class RepositoriesListFragment extends Fragment implements IRepositoriesListFragment {


    Unbinder unbinder;
    @BindView(R.id.rvRepositories_RepositoriesListFragment)
    RecyclerView rvRepositoriesRepositoriesListFragment;

    private RepositoriesListAdapter repositoriesListAdapter;
    private IRepositoriesListPresenter repositoriesListPresenter;
    private String name, companyName, repositoryCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("name") != null) {
                name = bundle.getString("name");
                companyName = bundle.getString("companyName");
                repositoryCount = bundle.getString("repositoryCount");
            }
        }

        setAdapter();
        if (repositoriesListPresenter == null) {
            repositoriesListPresenter = new RepositoriesListPresenter(getActivity(), this);
        }
        repositoriesListPresenter.getRepositories(name);

        return view;
    }

    private void setAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        repositoriesListAdapter = new RepositoriesListAdapter(getActivity());
        rvRepositoriesRepositoriesListFragment.setLayoutManager(layoutManager);
        rvRepositoriesRepositoriesListFragment.setAdapter(repositoriesListAdapter);
    }

    @Override
    public void showRepositories(List<Repositories> repositoriesList) {
        repositoriesListAdapter.setData(repositoriesList);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(companyName + " Repositories (" + repositoryCount + ")");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
