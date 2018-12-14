package com.example.android.materialme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView mSportsImage;
    private TextView mTitle;
    private TextView mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mSportsImage = findViewById(R.id.ivSportsImageDetail);
        mTitle = findViewById(R.id.titleDetail);
        mArticle = findViewById(R.id.subTitleDetail);

        Intent intento = getIntent();
        mTitle.setText(intento.getStringExtra("title"));
        mArticle.setText(intento.getStringExtra("article_text"));
        Glide.with(this)
                .load(intento.getIntExtra("image_resource", 0))
                .into(mSportsImage);
    }
}
