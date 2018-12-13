package com.example.rek.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String mOrderMessage;
    private final String ORDER_MESSAGE = "order_message";

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
                Intent intento = new Intent(MainActivity.this, OrderActivity.class);
                intento.putExtra(ORDER_MESSAGE, mOrderMessage);
                startActivity(intento);
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

        // Display toast based on selected item
        switch (id) {
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                break;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                break;
            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                break;
            case R.id.action_order:
                displayToast(getString(R.string.action_order_message));

                Intent intento = new Intent(MainActivity.this, OrderActivity.class);
                intento.putExtra(ORDER_MESSAGE, mOrderMessage);
                startActivity(intento);
                break;
            default:
                // Do nothing
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Display a toast with @param message
     *
     * @param message Message to display in the toast
     */
    public void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Callback for Donut image view
     *
     * @param view The image which was clicked
     */
    public void showDonutMessage(View view) {
        mOrderMessage = getString(R.string.donut_order_message);
        displayToast(mOrderMessage);
    }

    /**
     * Callback for Ice Cream image view
     *
     * @param view The image which was clicked
     */
    public void showIceCreamMessage(View view) {
        mOrderMessage = getString(R.string.ice_cream_order_message);
        displayToast(mOrderMessage);
    }

    /**
     * Callback for Froyo image view
     *
     * @param view The image which was clicked
     */
    public void showFroyoMessage(View view) {
        mOrderMessage = getString(R.string.froyo_order_message);
        displayToast(mOrderMessage);
    }
}
