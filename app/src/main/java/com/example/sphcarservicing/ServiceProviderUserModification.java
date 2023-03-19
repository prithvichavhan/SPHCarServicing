package com.example.sphcarservicing;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServiceProviderUserModification extends AppCompatActivity {

    // creating variables for
    // our ui components.
    private RecyclerView userSearchRecyclerView;
    //Button btnToSelectServices = findViewById(R.id.btnToSelectServices);

    // variable for our adapter
    // class and array list
    private UserSearchAdapter adapter;
    private ArrayList<UserModel> userSearchModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_user_modifiation);
        // initializing our variables.
        userSearchRecyclerView = findViewById(R.id.searchUser);
        // calling method to
        // build recycler view.
        buildRecyclerView();
    }

    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<UserModel> filteredList = new ArrayList<>();

        // running a for loop to compare elements.
        for (UserModel item : userSearchModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getUserEmail().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredList);
        }
    }

    private void buildRecyclerView() {

        // below line we are creating a new array list
        userSearchModelArrayList = new ArrayList<>();

        // below line is to add data to our array list.
        userSearchModelArrayList.add(new UserModel("john.w@example.com", "John Wick"));
        userSearchModelArrayList.add(new UserModel("chris.p@example.com", "Chris Pratt"));
        userSearchModelArrayList.add(new UserModel("jason.d@example.com", "Jason Derulo"));
        userSearchModelArrayList.add(new UserModel("justin.t@example.com", "Justn Timberlake"));
        userSearchModelArrayList.add(new UserModel("jack.w@example.com", "Jack William"));

        // initializing our adapter class.
        adapter = new UserSearchAdapter(userSearchModelArrayList, ServiceProviderUserModification.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        userSearchRecyclerView.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        userSearchRecyclerView.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        userSearchRecyclerView.setAdapter(adapter);
    }
}