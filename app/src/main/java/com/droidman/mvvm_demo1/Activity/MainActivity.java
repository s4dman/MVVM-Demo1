package com.droidman.mvvm_demo1.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.droidman.mvvm_demo1.Adapter.CategoriesAdapter;
import com.droidman.mvvm_demo1.Entity.CategoriesEntity;
import com.droidman.mvvm_demo1.R;
import com.droidman.mvvm_demo1.ViewModel.CategoriesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CategoriesViewModel categoriesViewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);
        AndroidNetworking.initialize(getApplicationContext());

        textView = findViewById(R.id.textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details= new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(details);
            }
        });

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
