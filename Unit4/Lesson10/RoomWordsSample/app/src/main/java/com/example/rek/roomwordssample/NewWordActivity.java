package com.example.rek.roomwordssample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {

    private EditText mEdtWordInput;
    private Button mBtnSave;

    // Tag for intent extra
    public static final String EXTRA_REPLY = "com.example.rek.roomwordssample.EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEdtWordInput = findViewById(R.id.edtWord);
        mBtnSave = findViewById(R.id.btnSave);
    }

    /**
     * Button callback sends entered word back to MainActivity
     * @param view  Button which called this method
     */
    public void saveWord(View view) {
        String newWord = mEdtWordInput.getText().toString();
        Intent intento = new Intent();

        if (newWord.length() > 0) {
            intento.putExtra(EXTRA_REPLY, newWord);
            setResult(RESULT_OK, intento);
        } else {
            setResult(RESULT_CANCELED, intento);
        }
        finish();
    }
}
