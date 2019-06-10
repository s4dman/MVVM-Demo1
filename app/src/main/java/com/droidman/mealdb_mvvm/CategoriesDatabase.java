package com.droidman.mealdb_mvvm;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = CategoriesEntity.class, version = 1, exportSchema = false)
public abstract class CategoriesDatabase extends RoomDatabase {

    private static CategoriesDatabase instance; // Singleton, means we can't create multiple instance of this DB. Instead we always use same instance everywhere in the app.
    public abstract CategoriesDao categoriesDao(); // Use this method to access our Dao

    /*
     * Synchronized means only one thread at a time can access this instance
     * this way you don't create two instances of this DB accidentally, where two different threads try to access this instance at the same time
     * */
    public static synchronized CategoriesDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, CategoriesDatabase.class, "categories_database")
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
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private CategoriesDao categoriesDao;

        public PopulateDbAsyncTask(CategoriesDatabase categoriesDatabase) {
            this.categoriesDao = categoriesDatabase.categoriesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoriesDao.insert(new CategoriesEntity("Beef", "https://www.themealdb.com/images/category/beef.png", "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]"));
            categoriesDao.insert(new CategoriesEntity("Chicken", "https://www.themealdb.com/images/category/chicken.png", "The chicken (Gallus gallus domesticus) is a type of domesticated fowl, a subspecies of the red junglefowl."));
            categoriesDao.insert(new CategoriesEntity("Desert", "https://www.themealdb.com/images/category/desert.png", "A desert is a barren area of landscape where little precipitation occurs and consequently living conditions are hostile for plant and animal life. The lack of vegetation exposes the unprotected surface of the ground to the processes of denudation."));
            return null;
        }
    }


}
