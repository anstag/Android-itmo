package ru.anstag.app.sport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ( position == 0) {
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    startActivity(intent);
                } if ( position == 1){
                    Intent intent1 = new Intent(MainActivity.this, CatOneActivity.class);
                    startActivity(intent1);
                } if ( position ==2){
                    Intent intent2 = new Intent(MainActivity.this, CatTwoActivity.class);
                    startActivity(intent2);
                }
            }
        };

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(itemClickListener);
    }

}
