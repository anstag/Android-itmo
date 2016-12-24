package ru.anstag.app.example3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SeriesList kList = new SeriesList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPressMe(View view) {
        TextView genre = (TextView) findViewById(R.id.genre);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        String spinner1 = String.valueOf(spinner.getSelectedItem());

        List<String> genreList = kList.getGenre(spinner1);

        StringBuilder genreMod = new StringBuilder();

        for (String genres: genreList){
            genreMod.append(genres).append('\n');
        }

        genre.setText(genreMod);
    }
}
