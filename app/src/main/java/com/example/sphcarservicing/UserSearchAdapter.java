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

public class UserSearchAdapter extends RecyclerView.Adapter  {

    ArrayList<UserModel> userSearchModelArrayList;

    ItemClickListener itemClickListener;
    LayoutInflater inflater;

    public UserSearchAdapter(ArrayList<UserModel> userSearchModelArrayList1, Context context, ItemClickListener itemClickListener1) {
        userSearchModelArrayList = userSearchModelArrayList1;
        inflater = LayoutInflater.from(context);
        itemClickListener = itemClickListener1;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_user_item, parent, false);
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).userName.setText(userSearchModelArrayList.get(position).getUserName());
        ((ViewHolder)holder).userEmail.setText(userSearchModelArrayList.get(position).getUserEmail());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return userSearchModelArrayList.size();
    }

    String getItem(int id){
        return userSearchModelArrayList.get(id).getUserName();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView userEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
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