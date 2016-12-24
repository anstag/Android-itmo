package ru.anstag.app.example2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloText;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloText = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);
    }

    public void onPressMe(View view) {
       if (mEditText.getText().length()==0){
               mHelloText.setText("Hello, Dude!");
       } else {
           mHelloText.setText("Привет, " + mEditText.getText() + "!");
       }
    }



}
