package com.example.dizzer.gittesttask.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dizzer.gittesttask.R;
import com.example.dizzer.gittesttask.model.Organization;
import com.example.dizzer.gittesttask.view.MainActivity;
import com.example.dizzer.gittesttask.view.RepositoriesListFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

    private List<Organization> itemList;
    private Context context;

    public SearchRecyclerViewAdapter(Context context) {
        itemList = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<Organization> itemList){
        if (this.itemList != null && itemList.size() > 0) {
            this.itemList.clear();
            this.itemList.addAll(itemList);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        int size = this.itemList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.itemList.remove(i);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(itemList.get(position).getAvatarUrl()).into(holder.ivPhotoSearchRVItem);
        holder.tvNameSearchRVItem.setText(itemList.get(position).getLogin());
        holder.tvAddressSearchRVItem.setText(itemList.get(position).getLocation());
        holder.tvProjectsUrlSearchRVItem.setText(itemList.get(position).getHtmlUrl());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPhoto_Search_RV_Item)
        ImageView ivPhotoSearchRVItem;
        @BindView(R.id.tvName_Search_RV_Item)
        TextView tvNameSearchRVItem;
        @BindView(R.id.tvAddress_Search_RV_Item)
        TextView tvAddressSearchRVItem;
        @BindView(R.id.tvProjectsUrl_Search_RV_Item)
        TextView tvProjectsUrlSearchRVItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity activity = (MainActivity) view.getContext();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", itemList.get(getAdapterPosition()).getLogin());
                    bundle.putString("companyName", itemList.get(getAdapterPosition()).getName());
                    bundle.putString("repositoryCount", itemList.get(getAdapterPosition()).getPublicRepos().toString());
                    FragmentManager fm = activity.getSupportFragmentManager();
                    RepositoriesListFragment repositoryListFragment = new RepositoriesListFragment();
                    repositoryListFragment.setArguments(bundle);
                    fm.beginTransaction()
                            .replace(R.id.container_main, repositoryListFragment)
                            .addToBackStack("REPOSITORY")
                            .commit();
                    fm.executePendingTransactions();
                }
            });
        }
    }
}
