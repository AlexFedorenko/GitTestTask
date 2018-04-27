package com.example.dizzer.gittesttask.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dizzer.gittesttask.R;
import com.example.dizzer.gittesttask.model.Organization;
import com.example.dizzer.gittesttask.presenter.ISearchPresenter;
import com.example.dizzer.gittesttask.presenter.SearchPresenter;
import com.example.dizzer.gittesttask.presenter.SearchRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

import static butterknife.OnTextChanged.Callback.AFTER_TEXT_CHANGED;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class SearchFragment extends Fragment implements ISearchFragment{

    @BindView(R.id.etSearch_SearchFragment)
    EditText etSearchSearchFragment;
    @BindView(R.id.rvSearchResult_SearchFragment)
    RecyclerView rvSearchResultSearchFragment;
    Unbinder unbinder;

    private SearchRecyclerViewAdapter searchRecyclerViewAdapter;
    private ISearchPresenter searchPresenter;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_repository, container, false);
        unbinder = ButterKnife.bind(this, view);
        setAdapter();
        if (searchPresenter == null) {
            searchPresenter = new SearchPresenter(getActivity(), this);
        }
        return view;
    }

    @OnTextChanged(value = R.id.etSearch_SearchFragment, callback = AFTER_TEXT_CHANGED)
    public void afterTextChanged(Editable s) {
        if (s.toString().length() > 2) {
            searchPresenter.getSearchResult(s.toString());
        }else {
            searchRecyclerViewAdapter.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(getActivity());
        rvSearchResultSearchFragment.setLayoutManager(layoutManager);
        rvSearchResultSearchFragment.setAdapter(searchRecyclerViewAdapter);
    }

    @Override
    public void showSearchResult(List<Organization> items) {
        searchRecyclerViewAdapter.setData(items);
    }
}
