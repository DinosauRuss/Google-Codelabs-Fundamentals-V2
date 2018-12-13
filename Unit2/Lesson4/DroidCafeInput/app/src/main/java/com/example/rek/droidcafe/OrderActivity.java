package com.example.rek.droidcafe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class OrderActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private TextView mTvOrderMessage;
    private Spinner mPhoneSpinner;
    private EditText mEdtPhone;

    private String mReceiveMessage;
    private final String ORDER_MESSAGE = "order_message";

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Receive intent and message from MainActivity
        Intent intento = getIntent();
        mReceiveMessage = intento.getStringExtra(ORDER_MESSAGE);

        // Change text based on message from MainActivity
        mTvOrderMessage = findViewById(R.id.tvOrderMessage);
        mTvOrderMessage.setText(mReceiveMessage);

        mEdtPhone = findViewById(R.id.edtPhone);
        mEdtPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    dialNumber();
                    handled = true;
                }
                return handled;
            }
        });

        mPhoneSpinner = findViewById(R.id.spnPhoneType);
        // Array adapter for spinner w/ default layout
        ArrayAdapter<CharSequence> adapto = ArrayAdapter.createFromResource(
                this, R.array.phone_labels_array, android.R.layout.simple_spinner_item);
        // Layout to use when list of choices appears
        adapto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (mPhoneSpinner != null) {
            mPhoneSpinner.setOnItemSelectedListener(this);
            mPhoneSpinner.setAdapter(adapto);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();
        makeToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Start implicit intent to dial number from edit text
     */
    private void dialNumber() {
        String phoneNum = mEdtPhone.getText().toString();
        if (phoneNum.length() > 0) {
            Intent intento = new Intent(Intent.ACTION_DIAL);
            phoneNum = "tel: " + phoneNum;
            Log.d(TAG, "Dialing: " + phoneNum);

            intento.setData(Uri.parse(phoneNum));
            if (intento.resolveActivity(getPackageManager()) != null) {
                startActivity(intento);
            } else {
                Log.d(TAG, "Cannot handle this" );
            }
        }
    }

    /**
     * Callback for radio button clicks
     * @param view  Radio Button which called this function
     */
    public void onRadioButtonClicked(View view) {
        // Is the button checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Which button
        switch (view.getId()) {
            case R.id.rbSameday:
                if (checked) {
                    makeToast(getString(R.string.rb_sameday_text));
                    break;
                }
            case R.id.rbNextday:
                if (checked) {
                    makeToast(getString(R.string.rb_nextday_text));
                    break;
                }
            case R.id.rbPickup:
                if (checked) {
                    makeToast(getString(R.string.rb_pickup_text));
                    break;
                }
            default: {
                // Do nothing
                break;
            }
        }
    }

    /**
     * Display a toast using @param message
     * @param message   The text to display
     */
    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
