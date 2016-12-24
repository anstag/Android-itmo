package ru.anstag.app.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "LifeCycle";
    private TextView mInfoLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mInfoLog = (TextView) findViewById(R.id.text);
        Toast.makeText(getApplicationContext(), "Метод onCreate", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Метод onStart", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "Метод onStop", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "Метод onPause", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Метод onResume", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "Метод onRestart", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Метод onDestroy", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onDestroy");
    }

    public void onClick (View v){
        switch (v.getId()){
            case R.id.pressMe:
                mInfoLog.setText("Приложение уже запущено!");
                break;

            case R.id.exit:
                finish();
                break;
            default:
                break;
        }
    }
}
