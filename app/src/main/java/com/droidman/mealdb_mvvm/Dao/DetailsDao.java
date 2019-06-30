package com.droidman.mealdb_mvvm.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.droidman.mealdb_mvvm.Entity.DetailsEntity;

import java.util.List;

@Dao
public interface DetailsDao {

    @Insert
    void insertMealDetails(DetailsEntity detailsEntity);

    @Query("SELECT * FROM meal_details")
    LiveData<List<DetailsEntity>> getMeal();
}
