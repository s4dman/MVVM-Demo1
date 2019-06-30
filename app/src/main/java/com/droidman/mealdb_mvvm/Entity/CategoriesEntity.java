package com.droidman.mealdb_mvvm.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "categories_table")
public class CategoriesEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String imageUrl;
    private String description;

    public CategoriesEntity(String name, String imageUrl, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }
}
