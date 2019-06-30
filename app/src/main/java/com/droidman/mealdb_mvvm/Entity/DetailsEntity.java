package com.droidman.mealdb_mvvm.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "meal_details")
public class DetailsEntity {

    @PrimaryKey
    private int mealId;
    private String mealName;
    private String mealCategory;
    private String mealArea;
    private String mealInstruction;
    private String mealImage;
    private String mealYoutube;

    public DetailsEntity(int mealId, String mealName, String mealCategory, String mealArea, String mealInstruction, String mealImage, String mealYoutube) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.mealArea = mealArea;
        this.mealInstruction = mealInstruction;
        this.mealImage = mealImage;
        this.mealYoutube = mealYoutube;
    }

    public int getMealId() {
        return mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public String getMealArea() {
        return mealArea;
    }

    public String getMealInstruction() {
        return mealInstruction;
    }

    public String getMealImage() {
        return mealImage;
    }

    public String getMealYoutube() {
        return mealYoutube;
    }
}
