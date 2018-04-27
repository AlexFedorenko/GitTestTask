package com.example.dizzer.gittesttask.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dizzer.gittesttask.R;
import com.example.dizzer.gittesttask.model.Repositories;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class RepositoriesListAdapter extends RecyclerView.Adapter<RepositoriesListAdapter.ViewHolder> {

    private Context context;
    private List<Repositories> itemList;

    public RepositoriesListAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
    }

    public void setData(List<Repositories> itemList) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.repository_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNameRepositoryRVItem.setText(itemList.get(position).getName());
        holder.tvDescriptionRepositoryRVItem.setText(itemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName_Repository_RV_Item)
        TextView tvNameRepositoryRVItem;
        @BindView(R.id.tvDescription_Repository_RV_Item)
        TextView tvDescriptionRepositoryRVItem;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
