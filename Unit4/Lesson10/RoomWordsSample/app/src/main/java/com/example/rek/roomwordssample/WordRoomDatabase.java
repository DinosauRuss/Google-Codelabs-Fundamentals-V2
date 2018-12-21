package com.example.rek.roomwordssample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase INSTANCE;

    // Callback to populate database with sample data only upon opening
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Singleton pattern to create a database
     * @param context   Context from the app
     * @return  RoomDatabase object
     */
    public static WordRoomDatabase getdatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Abstract getter method for Dao
     * @return  Dao object
     */
    public abstract WordDao wordDao();


    /**
     * Populate the database in the background
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private WordDao mDao;

        // Sample data to populate database
        String[] animals = {"dolphin", "cobra", "alligator", "cheetah"};

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Only populate database if it is currently empty
            if (mDao.getAnyWord().length < 1) {
                // Start app with clean database
                mDao.deleteAll();

                // Add words to the database using Dao
                for (String animal : animals) {
                    Word wordo = new Word(animal);
                    mDao.insertWord(wordo);
                }
            }
            return null;
        }
    }

}
