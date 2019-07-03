package com.droidman.mvvm_demo1.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.droidman.mvvm_demo1.Entity.DetailsEntity;

import java.util.List;

@Dao
public interface DetailsDao {

    @Insert
    void insertMealDetails(DetailsEntity detailsEntity);

    @Query("SELECT * FROM meal_details")
    LiveData<List<DetailsEntity>> getMeal();
}
