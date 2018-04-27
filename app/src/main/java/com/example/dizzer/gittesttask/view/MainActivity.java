package com.example.dizzer.gittesttask.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dizzer.gittesttask.R;

public class MainActivity extends AppCompatActivity {

    private SearchFragment searchFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
