package com.example.sphcarservicing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sphcarservicing.R;
import com.example.sphcarservicing.UserModel;

import java.util.ArrayList;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.ViewHolder> {

    private ArrayList<UserModel> userSearchModelArrayList;

    public UserSearchAdapter(ArrayList<UserModel> userSearchModelArrayList, Context context) {
        this.userSearchModelArrayList = userSearchModelArrayList;
    }


    public void filterList(ArrayList<UserModel> filterList) {
        // below line is to add our filtered
        // list in our course array list.
        userSearchModelArrayList = filterList;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_sp_item, parent, false);
        return new UserSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserSearchAdapter.ViewHolder holder, int position) {
        UserModel umodel = userSearchModelArrayList.get(position);
        holder.userName.setText(umodel.getUserName());
        holder.userEmail.setText(umodel.getUserEmail());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return userSearchModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userEmail;
        private final TextView userName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userEmail = itemView.findViewById(R.id.userName);
            userName = itemView.findViewById(R.id.userEmail);
        }
    }
}