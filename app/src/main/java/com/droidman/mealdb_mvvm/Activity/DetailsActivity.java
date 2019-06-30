package com.droidman.mealdb_mvvm.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.droidman.mealdb_mvvm.Entity.DetailsEntity;
import com.droidman.mealdb_mvvm.R;
import com.droidman.mealdb_mvvm.ViewModel.DetailsViewModel;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";

    private ImageView img;
    private TextView title, origin, category, video, instruction;
    private DetailsViewModel detailsViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_details_activity);
        init();

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.getMealDetails().observe(this, new Observer<List<DetailsEntity>>() {
            @Override
            public void onChanged(@Nullable List<DetailsEntity> detailsEntities) {

                Glide.with(DetailsActivity.this)
                        .load(detailsEntities.get(0).getMealImage())
                        .into(img);
                title.setText(detailsEntities.get(0).getMealName());
                origin.setText(detailsEntities.get(0).getMealArea());
                category.setText(detailsEntities.get(0).getMealCategory());
                video.setText(detailsEntities.get(0).getMealYoutube());
                instruction.setText(detailsEntities.get(0).getMealInstruction());
            }
        });
    }

    private void init() {
        img = findViewById(R.id.img_details);
        title = findViewById(R.id.title_details);
        origin = findViewById(R.id.origin_details);
        category = findViewById(R.id.category_details);
        video = findViewById(R.id.video_details);
        instruction = findViewById(R.id.instruction_details);
    }
}
