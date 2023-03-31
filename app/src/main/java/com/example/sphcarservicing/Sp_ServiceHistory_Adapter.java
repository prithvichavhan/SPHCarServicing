package com.example.sphcarservicing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sp_ServiceHistory_Adapter extends RecyclerView.Adapter{

    ArrayList<Sp_ServiceHistory_Model> ServiceHistory_Model_ArrayList;

    ItemClickListener itemClickListener;
    LayoutInflater inflater;

    public Sp_ServiceHistory_Adapter(ArrayList<Sp_ServiceHistory_Model> ServiceHistory_Model_ArrayList1
            , Context context, ItemClickListener itemClickListener1) {
        ServiceHistory_Model_ArrayList = ServiceHistory_Model_ArrayList1;
        inflater = LayoutInflater.from(context);
        itemClickListener = itemClickListener1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sp_service_history_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).serviceProviderName.setText(ServiceHistory_Model_ArrayList.get(position).getSpName());
        ((ViewHolder)holder).serviceProviderAddress.setText(ServiceHistory_Model_ArrayList.get(position).getSpAddress());
        ((ViewHolder)holder).AppointDate.setText(ServiceHistory_Model_ArrayList.get(position).getBdate());
        ((ViewHolder)holder).Service.setText(ServiceHistory_Model_ArrayList.get(position).getservices());
    }

    @Override
    public int getItemCount() {
        return ServiceHistory_Model_ArrayList.size();
    }
    public String getItem(int position){return ServiceHistory_Model_ArrayList.get(position).getUemail();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceProviderName;
        TextView serviceProviderAddress;
        TextView AppointDate;
        TextView Service;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceProviderName = itemView.findViewById(R.id.serviceProviderName);
            serviceProviderAddress = itemView.findViewById(R.id.serviceProviderAddress);
            AppointDate = itemView.findViewById(R.id.AppointDate);
            Service = itemView.findViewById(R.id.Service);
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






