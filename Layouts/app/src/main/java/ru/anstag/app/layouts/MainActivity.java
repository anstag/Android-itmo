package ru.anstag.app.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickA1(View view) {
        //super.onCreate(Bundle.EMPTY);
        setContentView(R.layout.activity_main2);
    }

    public void onClickA2(View view) {
        setContentView(R.layout.activity_main3);
    }

    public void onClickA3(View view) {
        setContentView(R.layout.activity_main4);
    }
}
