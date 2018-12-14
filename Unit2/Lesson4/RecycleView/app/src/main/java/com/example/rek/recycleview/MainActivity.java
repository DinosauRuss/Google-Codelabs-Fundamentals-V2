package com.example.rek.recycleview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mWordList;
    private WordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sample data for recycler view
        mWordList = new ArrayList<>();
        mWordList = addInitialData(mWordList);

        // Recycler view
        final RecyclerView recyclo = findViewById(R.id.rvRecyclo);
        mAdapter = new WordAdapter(this, mWordList);
        recyclo.setAdapter(mAdapter);
        recyclo.setLayoutManager(new LinearLayoutManager(this));

        // Fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Current size of data set
                int dataSize = mWordList.size();
                // Add element to end of data set
                mWordList.add(dataSize, "Word " + dataSize);
                // Notify adapter and scroll to new word
                recyclo.getAdapter().notifyItemInserted(dataSize);
                recyclo.smoothScrollToPosition(dataSize);
            }
        });
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
        if (id == R.id.action_reset) {
            mWordList = resetData(mWordList, mAdapter);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Add 20 generic word items to ArrayList and return it
     * @param currentData   ArrayList to add to
     * @return              ArrayList with items added
     */
    private ArrayList<String> addInitialData(ArrayList<String> currentData) {
        for (int i=0; i<20; i++) {
            currentData.add("Word " + i);
        }
        return currentData;
    }

    /**
     * Reset ArrayList to initial data
     * @param currentData   ArrayList to change
     * @param adapter       Adapter controlling the data and recyclerview
     * @return              ArrayList with new data
     */
    private ArrayList<String> resetData(ArrayList<String> currentData, WordAdapter adapter) {
        if (currentData.size() > 0) {
            // Clear current data and re-populate with initial data set
            currentData.clear();
            currentData = addInitialData(currentData);
            adapter.notifyDataSetChanged();
        }
        return currentData;
    }
}
