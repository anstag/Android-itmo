package ru.anstag.app.example1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // anstag
    private Button mYesButton;
    private Button mNoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // anstag
        mYesButton = (Button) findViewById(R.id.yes_button);
            mYesButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Toast.makeText(MainActivity.this,
                            R.string.correct_answer,
                            Toast.LENGTH_LONG).show();
                }
            });

        mNoButton = (Button) findViewById(R.id.no_button);
            mNoButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Toast.makeText(MainActivity.this,
                            R.string.incorrect_answer,
                            Toast.LENGTH_LONG).show();
                }
            });
    }
}
