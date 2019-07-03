package com.droidman.mvvm_demo1.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.droidman.mvvm_demo1.Dao.DetailsDao;
import com.droidman.mvvm_demo1.Database.DetailsDatabase;
import com.droidman.mvvm_demo1.Entity.DetailsEntity;

import java.util.List;

public class DetailsRepository {

    private DetailsDao detailsDao;
    private LiveData<List<DetailsEntity>> mealDetails;

    public DetailsRepository(Application application) {

        DetailsDatabase detailsDatabase = DetailsDatabase.getInstance(application);
        this.detailsDao = detailsDatabase.detailsDao();
        this.mealDetails = detailsDao.getMeal();
    }

    public void insert(DetailsEntity detailsEntity){
        new InsertAsyncTask(detailsDao).execute(detailsEntity);
    }

    public LiveData<List<DetailsEntity>> getMealDetails(){
        return mealDetails;
    }

    private static class InsertAsyncTask extends AsyncTask<DetailsEntity, Void, Void> {
        private DetailsDao detailsDao;

        public InsertAsyncTask(DetailsDao detailsDao) {
            this.detailsDao = detailsDao;
        }

        @Override
        protected Void doInBackground(DetailsEntity... detailsEntities) {
            detailsDao.insertMealDetails(detailsEntities[0]);
            return null;
        }
    }
}
