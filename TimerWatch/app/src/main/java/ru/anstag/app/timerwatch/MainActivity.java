package ru.anstag.app.timerwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("running", running);
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running  =false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    public void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.textView);

        final Handler handler = new Handler();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    int hours = seconds/3600;
                    int minutes = (seconds%3600)/60;
                    int secs = seconds%60;

                    String time = String.format("%d:%02d:%02d", hours, minutes, secs);

                    timeView.setText(time);

                    if (running){
                        seconds++;
                    }
                    handler.postDelayed(this, 1000); // Скорость
                }
            });

    }
}
