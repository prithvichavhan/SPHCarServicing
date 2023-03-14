package com.example.sphcarservicing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<SearchModel> searchModelArrayList;

    public SearchAdapter(ArrayList<SearchModel> searchModelArrayList, Context context) {
        this.searchModelArrayList = searchModelArrayList;
    }

    public void filterList(ArrayList<SearchModel> filterList) {
        // below line is to add our filtered
        // list in our course array list.
        searchModelArrayList = filterList;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_sp_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        SearchModel model = searchModelArrayList.get(position);
        holder.serviceProviderName.setText(model.getSpName());
        holder.serviceProviderAddress.setText(model.getSpAddress());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return searchModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView serviceProviderName;
        private final TextView serviceProviderAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceProviderName = itemView.findViewById(R.id.serviceProviderName);
            serviceProviderAddress = itemView.findViewById(R.id.serviceProviderAddress);
        }
    }
}