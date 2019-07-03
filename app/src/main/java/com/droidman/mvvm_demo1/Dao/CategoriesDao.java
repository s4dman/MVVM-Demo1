package com.droidman.mvvm_demo1.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.droidman.mvvm_demo1.Entity.CategoriesEntity;
import java.util.List;

@Dao
public interface CategoriesDao {

    @Insert
    void insert(CategoriesEntity categoriesEntity);

    @Query("SELECT * FROM categories_table ORDER BY id DESC")
    LiveData<List<CategoriesEntity>> getAll();
    /*
     * LiveData observes any changes in the CategoriesEntity,
     * then List<CategoriesEntity> will be automatically updated
     * and notify the Activity/Fragment
     */
}
