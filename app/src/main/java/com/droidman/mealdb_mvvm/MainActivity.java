package com.droidman.mealdb_mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CategoriesViewModel categoriesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());

        RecyclerView categoriesRecyclerView = findViewById(R.id.recycler_categories);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoriesRecyclerView.setHasFixedSize(true);

        final CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoriesViewModel.getAllCategories().observe(this, new Observer<List<CategoriesEntity>>() {
            @Override
            public void onChanged(@Nullable List<CategoriesEntity> categoriesEntities) {
                categoriesAdapter.setAllCategories(categoriesEntities);
            }




        });
    }
}
