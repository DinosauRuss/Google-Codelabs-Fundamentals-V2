package com.example.rek.roomwordssample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.myViewHolder> {

    private LayoutInflater mInflater;

    // Cached copy of data
    private List<Word> mWordList;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int pos) {
        if (mWordList != null) {
            Word current = mWordList.get(pos);
            holder.mTvPrimary.setText(current.getWord());
        } else {
            // If data is not present
            holder.mTvPrimary.setText("No Word");
        }
    }

    public void setWords(List<Word> words) {
        mWordList = words;
        notifyDataSetChanged();
    }

    public Word getWordAtPosition(int position) {
        return mWordList.get(position);
    }

    @Override
    public int getItemCount() {
        // Prevent method from returning null
        if (mWordList != null) {
            return mWordList.size();
        } else {
            return 0;
        }
    }


    class myViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvPrimary;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvPrimary = itemView.findViewById(R.id.tvMain);
        }
    }

}
