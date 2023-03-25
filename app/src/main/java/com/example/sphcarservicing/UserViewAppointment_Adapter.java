package com.example.sphcarservicing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserViewAppointment_Adapter extends RecyclerView.Adapter{

    ArrayList<UserViewAppointment_Model> UserViewAppointment_Model_ArrayList;

    ItemClickListener itemClickListener;
    LayoutInflater inflater;

    public UserViewAppointment_Adapter(ArrayList<UserViewAppointment_Model> UserViewAppointment_Model_ArrayList1
            , Context context, ItemClickListener itemClickListener1) {
        UserViewAppointment_Model_ArrayList = UserViewAppointment_Model_ArrayList1;
        inflater = LayoutInflater.from(context);
        itemClickListener = itemClickListener1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_appointment_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).serviceProviderName.setText(UserViewAppointment_Model_ArrayList.get(position).getUemail());
    }

    @Override
    public int getItemCount() {
        return UserViewAppointment_Model_ArrayList.size();
    }

    String getItem(int id){
        return UserViewAppointment_Model_ArrayList.get(id).getUemail();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceProviderName;
        TextView serviceProviderAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceProviderName = itemView.findViewById(R.id.serviceProviderName);
            serviceProviderAddress = itemView.findViewById(R.id.serviceProviderAddress);
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
