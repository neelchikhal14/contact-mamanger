package com.example.contactsmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Data Source
    private ContactDatabase contactDatabase;
    private ArrayList<Contact> contactArrayList = new ArrayList<>();

    // Adapter
    private MyAdapter myAdapter;

    // Binding

    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers mainActivityClickHandlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityClickHandlers = new MainActivityClickHandlers(this);
        activityMainBinding.setClickHandler(mainActivityClickHandlers);

        // Recycler View
        RecyclerView recyclerView = activityMainBinding.recyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);



        //Database
        contactDatabase = ContactDatabase.getInstance(this);

        //View Model
        ContactViewModel viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        // Inser a new contact - for testing
        Contact c1 = new Contact("John","john@abc.com");

        viewModel.addNewContact(c1);

        // Load data from db
        viewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

                contactArrayList.clear();

                for(Contact c:contacts){
                    Log.v("TAG",c.getName());
                    contactArrayList.add(c);
                }

                // notify recyclerview
                myAdapter.notifyDataSetChanged();
            }
        });

        //Adapter
        myAdapter = new MyAdapter(contactArrayList);

        // Link Adapter and Recycler view
        recyclerView.setAdapter(myAdapter);

        //swipw to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // delete on Swipe left
               Contact c = contactArrayList.get(viewHolder.getAdapterPosition());

               viewModel.deleteNewContact(c);

            }
        }).attachToRecyclerView(recyclerView);
    }
}