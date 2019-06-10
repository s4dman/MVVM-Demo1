package com.droidman.mealdb_mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CategoriesDao {

    @Insert
    void insert(CategoriesEntity categoriesEntity);

    @Update
    void update(CategoriesEntity categoriesEntity);

    @Delete
    void delete(CategoriesEntity categoriesEntity);

    @Query("DELETE FROM categories_table")
    void deleteAll();

    @Query("SELECT * FROM categories_table ORDER BY id DESC")
    LiveData<List<CategoriesEntity>> getAll();
    /*
    * LiveData observes any changes in the CategoriesEntity,
    * then List<CategoriesEntity> will be automatically updated
    * and notify the Activity/Fragment
    */
}
