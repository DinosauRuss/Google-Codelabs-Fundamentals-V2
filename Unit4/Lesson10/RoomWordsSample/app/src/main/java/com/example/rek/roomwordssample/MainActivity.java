package com.example.rek.roomwordssample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;

    private RecyclerView mRecyclerView;
    private WordListAdapter adapto;

    // To receive Intent data
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(getApplicationContext(), NewWordActivity.class);
                startActivityForResult(intento, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        // Initialize RecyclerView
        mRecyclerView = findViewById(R.id.rvPrimaryRecycler);
        initRecyclerView();

        // Set up ViewModel
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        initViewModel();

        // Allow swiping items in RecyclerView
        attachTouchHelper();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear_data) {
            // Delete all existing data
            mWordViewModel.deleteAll();

            // Toast for confirmation
            Toast.makeText(this, getString(R.string.toast_clear_data),
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up recyclerview with adapter and layout manager
     */
    private void initRecyclerView() {
        adapto = new WordListAdapter(this);
        mRecyclerView.setAdapter(adapto);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE) {
            String wordToAdd = data.getStringExtra(NewWordActivity.EXTRA_REPLY);
            mWordViewModel.insertWord( new Word(wordToAdd) );
        } else {
            Toast.makeText(getApplicationContext(),
                    R.string.empty_not_saved, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Associate ViewModel with this ui
     * and set up data observer
     */
    private void initViewModel() {
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                // Update cached copy of words in recyclerview adapter
                adapto.setWords(words);
            }
        });
    }

    /**
     * Attach an ItemTouchHelper to detect swipes
     * and delete swiped word from database
     */
    private void attachTouchHelper() {
        ItemTouchHelper helpo = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        // Delete swiped word
                        int position = viewHolder.getAdapterPosition();
                        Word wordToDelete = adapto.getWordAtPosition(position);
                        mWordViewModel.deleteWord(wordToDelete);

                        // Toast to confirm
                        Toast.makeText(MainActivity.this,
                                wordToDelete.getWord() + " Deleted",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
        helpo.attachToRecyclerView(mRecyclerView);
    }
}
