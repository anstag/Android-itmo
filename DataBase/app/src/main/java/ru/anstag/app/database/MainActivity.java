package ru.anstag.app.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText name, phone;
    Button insert, show, delete;
    DataBase dataBase;
    TextView showData;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dataBase = new DataBase(this);
        name = (EditText) findViewById(R.id.nameEdit);
        phone = (EditText) findViewById(R.id.phoneEdit);
        insert =(Button) findViewById(R.id.insertBtn);
        show =(Button) findViewById(R.id.showBtn);
        delete =(Button) findViewById(R.id.deleteBtn);
        showData = (TextView) findViewById(R.id.showData);

        final SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();


        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", name.getText().toString());
                contentValues.put("phone", phone.getText().toString());
                sqLiteDatabase.insert("Contact", null, contentValues);

                Log.d("Log", "Data inserted");
            }
        });

        show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Cursor cursor = sqLiteDatabase.query("Contact", null, null, null, null, null, null);

                data = "";
                showData.setText(data);

                if (cursor.moveToFirst()){
                    do {
                        data += data + "id: " + cursor.getInt(cursor.getColumnIndex("id")) +
                                " name: " + cursor.getString(cursor.getColumnIndex("name")) +
                                " phone: " + cursor.getInt(cursor.getColumnIndex("phone")) + "\n";

//                        Log.d("Log",
//                                "id: " + cursor.getInt(cursor.getColumnIndex("id")) +
//                                " name: " + cursor.getString(cursor.getColumnIndex("name")) +
//                                " phone: " + cursor.getInt(cursor.getColumnIndex("phone")));
                    } while (cursor.moveToNext());

                    showData.setText(data);

                } else {
                    Log.d("Log", "Table not found!");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                data = "";
                showData.setText(data);
                sqLiteDatabase.delete("Contact", null, null);
                Log.d("Log", "Table deleted!");
            }
        });



                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
