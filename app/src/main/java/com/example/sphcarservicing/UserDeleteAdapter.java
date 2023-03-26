package com.example.sphcarservicing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserDeleteAdapter extends RecyclerView.Adapter{
    ArrayList<UserModel> userDeleteModelArrayList;

    ItemClickListener itemClickListener;
    LayoutInflater inflater;

    public UserDeleteAdapter(ArrayList<UserModel> userDeleteModelArrayList1, Context context, ItemClickListener itemClickListener1) {
        userDeleteModelArrayList = userDeleteModelArrayList1;
        inflater = LayoutInflater.from(context);
        itemClickListener = itemClickListener1;
    }

    public void filterList(ArrayList<UserModel> filterList) {
        // below line is to add our filtered
        // list in our course array list.
        userDeleteModelArrayList = filterList;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.delete_user_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).userName.setText(userDeleteModelArrayList.get(position).getUserName());
        ((ViewHolder)holder).userEmail.setText(userDeleteModelArrayList.get(position).getUserEmail());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return userDeleteModelArrayList.size();
    }

    String getItem(int id){
        return userDeleteModelArrayList.get(id).getUserName();
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
