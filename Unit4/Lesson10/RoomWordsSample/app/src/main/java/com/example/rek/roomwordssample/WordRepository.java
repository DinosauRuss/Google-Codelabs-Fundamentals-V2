package com.example.rek.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {

    private WordDao mDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application app) {
        WordRoomDatabase db = WordRoomDatabase.getdatabase(app);
        mDao = db.wordDao();
        mAllWords = mDao.getAllWords();
    }

    /**
     * Wrapper method to get all words from Dao
     * @return  All Word objects from database
     */
    public LiveData<List<Word>> getAllWords() {
        return mDao.getAllWords();
    }

    public void insertWord(Word word) {
        new insertAsyncTask(mDao).execute(word);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mDao).execute();
    }

    public void deleteWord(Word word) {
        new deleteSingleAsyncTask(mDao).execute(word);
    }


    /**
     * Class to insert word in AsyncTask thread
     */
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncDao;

        insertAsyncTask(WordDao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncDao.insertWord(words[0]);
            return null;
        }
    }

    /**
     * Class to clear database in AsyncTask thread
     */
    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao mAsyncDao;

        deleteAllAsyncTask(WordDao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncDao.deleteAll();
            return null;
        }
    }

    /**
     * Class to delete single word from database in AsyncTask thread
     */
    private static class deleteSingleAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncDao;

        deleteSingleAsyncTask(WordDao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncDao.deleteWord(words[0]);
            return null;
        }
    }

}
