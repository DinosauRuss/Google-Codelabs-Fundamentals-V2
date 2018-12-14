package com.example.rek.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<String> mWordData;

    public WordAdapter(Context context, ArrayList<String> wordData) {
        mInflater = LayoutInflater.from(context);
        mWordData = wordData;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.word_item, viewGroup, false);
        return new WordViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int pos) {
        String currentWord = mWordData.get(pos);
        wordViewHolder.mTvWord.setText(currentWord);
    }

    @Override
    public int getItemCount() {
        return mWordData.size();
    }


    /**
     * Custom viewholder class for recycler view
     */
    public class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTvWord;
        private WordAdapter mAdapter;

        public WordViewHolder(@NonNull View itemView, WordAdapter adapter) {
            super(itemView);
            mTvWord = itemView.findViewById(R.id.tvWord);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get position of clicked item
            int mPos = getLayoutPosition();
            // Use position to access word from the data
            String currentWord = mWordData.get(mPos);
            // Change the word
            mWordData.set(mPos, currentWord + " Clicked!");
            mAdapter.notifyDataSetChanged();
        }
    }

}
