package ru.anstag.app.player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("С Новым Годом!");

        final Button btnStart = (Button) findViewById(R.id.buttonStart);
        final Button btnStop = (Button) findViewById(R.id.buttonStop);

        // запуск службы
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // явный вызов службы
               startService(new Intent(MainActivity.this, PlayerService.class));
            }
        });

        // остановка службы
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // явный вызов службы
               stopService(new Intent(MainActivity.this, PlayerService.class));
            }
        });

    }
}
