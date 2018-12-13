package com.example.rek.scrollingtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView mTvArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvArticle = findViewById(R.id.tvArticle);
        registerForContextMenu(mTvArticle);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context, menu);
        }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.context_edit:
                makeToast(getString(R.string.edit_toast_msg));
                return true;
            case R.id.context_share:
                makeToast(getString(R.string.share_toast_msg));
                return true;
            case R.id.context_delete:
                makeToast(getString(R.string.delete_toast_msg));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
