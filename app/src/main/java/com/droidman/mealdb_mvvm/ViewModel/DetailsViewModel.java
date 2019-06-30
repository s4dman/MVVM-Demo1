package com.droidman.mealdb_mvvm.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.droidman.mealdb_mvvm.Entity.DetailsEntity;
import com.droidman.mealdb_mvvm.Repository.DetailsRepository;

import java.util.List;

public class DetailsViewModel extends AndroidViewModel {

    private DetailsRepository detailsRepository;
    private LiveData<List<DetailsEntity>> mealDetails;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        this.detailsRepository = new DetailsRepository(application);
        this.mealDetails = detailsRepository.getMealDetails();
    }

    public void insert(DetailsEntity detailsEntity){
        detailsRepository.insert(detailsEntity);
    }

    public LiveData<List<DetailsEntity>> getMealDetails(){
        return mealDetails;
    }
}
