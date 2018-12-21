package com.example.rek.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    // Repository to retrieve data
    private WordRepository mRepo;
    // Private cache of data from repository
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);

        mRepo = new WordRepository(application);
        mAllWords = mRepo.getAllWords();
    }

    /**
     * Getter method to hide dao implemenation from ui
     * @return  All words from database
     */
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    /**
     * Insert word into database, hides dao implementation from ui
     * @param word  Word object to insert in database
     */
    public void insertWord(Word word) {
        mRepo.insertWord(word);
    }

    /**
     * Clear database, hides dao implementation from ui
     */
    public void deleteAll() {
        mRepo.deleteAll();
    }

    /**
     * Delete word from database, hides dao implementation from ui
     * @param word  Word object to delete
     */
    public void deleteWord(Word word) {
        mRepo.deleteWord(word);
    }



}
