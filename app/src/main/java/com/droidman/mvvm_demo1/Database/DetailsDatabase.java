package com.droidman.mvvm_demo1.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.droidman.mvvm_demo1.Dao.DetailsDao;
import com.droidman.mvvm_demo1.Entity.DetailsEntity;

@Database(entities = DetailsEntity.class, version = 1, exportSchema = false)
public abstract class DetailsDatabase extends RoomDatabase {

    private static DetailsDatabase instance;
    public abstract DetailsDao detailsDao();

    public static synchronized DetailsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DetailsDatabase.class, "meal_details_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDb(instance).execute();
        }
    };

    private static class PopulateDb extends AsyncTask<Void, Void, Void> {

        private DetailsDao detailsDao;

        public PopulateDb(DetailsDatabase detailsDatabase) {
            this.detailsDao = detailsDatabase.detailsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            detailsDao.insertMealDetails(new DetailsEntity(1, "Teriyaki Chicken Casserole", "Chicken", "Japanese", "Preheat oven to 350° F. …remaining sauce. Enjoy!", "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg", "https://www.youtube.com"));
            return null;
        }
    }
}
