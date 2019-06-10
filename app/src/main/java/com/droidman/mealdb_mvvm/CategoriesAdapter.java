package com.droidman.mealdb_mvvm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private Context mContext;
    private List<CategoriesEntity> allCategories = new ArrayList<>();


    public CategoriesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        CategoriesEntity currentCategory = allCategories.get(i);

        Glide.with(mContext)
                .asBitmap()
                .load(currentCategory.getImageUrl())
                .into(viewHolder.catergoriesImage);
        viewHolder.categoriesName.setText(currentCategory.getName());
        viewHolder.categoriesDesc.setText(currentCategory.getDescription());
    }

    @Override
    public int getItemCount() {
        return allCategories.size();
    }


    public void setAllCategories(List<CategoriesEntity> allCategories) {
        this.allCategories = allCategories;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView catergoriesImage;
        private TextView categoriesName, categoriesDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catergoriesImage = itemView.findViewById(R.id.categories_imageView);
            categoriesName = itemView.findViewById(R.id.categories_name);
            categoriesDesc = itemView.findViewById(R.id.categories_desc);
        }
    }
}
