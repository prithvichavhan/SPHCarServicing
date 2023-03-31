package com.example.sphcarservicing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter {
    ArrayList<SearchModel> searchModelArrayList;

    ItemClickListener itemClickListener;
    LayoutInflater inflater;

    public SearchAdapter(ArrayList<SearchModel> searchModelArrayList1, Context context,ItemClickListener itemClickListener1) {
        searchModelArrayList = searchModelArrayList1;
        inflater = LayoutInflater.from(context);
        itemClickListener = itemClickListener1;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_sp_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).serviceProviderName.setText(searchModelArrayList.get(position).getSpName());
        ((ViewHolder)holder).serviceProviderAddress.setText(searchModelArrayList.get(position).getSpAddress());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return searchModelArrayList.size();
    }

    String getItem(int id){
        return searchModelArrayList.get(id).getSpName();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceProviderName;
        TextView serviceProviderAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceProviderName = itemView.findViewById(R.id.userservicedname);
            serviceProviderAddress = itemView.findViewById(R.id.useremail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener!=null)
                        itemClickListener.onItemClick(view,getAdapterPosition());
                }
            });
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view,int position);
    }
}