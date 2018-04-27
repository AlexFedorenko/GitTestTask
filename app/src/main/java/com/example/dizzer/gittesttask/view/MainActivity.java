package com.example.dizzer.gittesttask.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dizzer.gittesttask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSearchFragment();
        loadToolbar();
    }

    private void setSearchFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("SEARCH");
        if (fragment == null) {
            searchFragment = new SearchFragment();
            fm.beginTransaction()
                    .replace(R.id.container_main, searchFragment, "SEARCH")
                    .commitAllowingStateLoss();
            fm.executePendingTransactions();
        } else {
            searchFragment = (SearchFragment) fragment;
        }
    }

    private void loadToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
}


