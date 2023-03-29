package com.example.sphcarservicing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServiceProviderUserModification extends AppCompatActivity implements UserSearchAdapter.ItemClickListener{

    DatabaseHelper dbh;
    RecyclerView userSearchRecyclerView;
    UserSearchAdapter adapter;

    ArrayList<UserModel> userSearchModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_user_modifiation);

        buildRecyclerView();

        Button addUser = findViewById(R.id.addUser);
        Button sp_home = findViewById(R.id.sp_home);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceProviderUserModification.this,Register_user.class));
            }
        });
        sp_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceProviderUserModification.this,ServiceProviderHome.class));
            }
        });
    }

    public void buildRecyclerView() {

        // below line we are creating a new array list
        userSearchModelArrayList = new ArrayList<>();
        dbh = new DatabaseHelper(this);


        Cursor cursor = dbh.viewUserData("0");
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                str1.append(cursor.getString(0)); //email
                str2.append(cursor.getString(1)); //name

                // below line is to add data to our array list.
                userSearchModelArrayList.add(new UserModel(String.valueOf(str1), String.valueOf(str2)));
                str1.setLength(0);
                str2.setLength(0);
            }
        }


        userSearchRecyclerView = findViewById(R.id.searchUser);

        // adding layout manager to our recycler view.

        userSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initializing our adapter class.
        adapter = new UserSearchAdapter(userSearchModelArrayList,
                this,this);
        // setting adapter to
        // our recycler view.
        userSearchRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("U_NAME", adapter.getItem(position));
        editor.commit();

        startActivity(new Intent(ServiceProviderUserModification.this,EditProfile2.class));
    }
}