package ru.anstag.app.notes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static ru.anstag.app.notes.R.id.container;

public class MainActivity extends AppCompatActivity {

    DatabaseManager dbMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddNote.class));
            }
        });


        //ListView listView = (ListView) findViewById(R.id.listView);

        dbMgr = new DatabaseManager(this);


        Cursor cursor = dbMgr.getNotesCursor();
        startManagingCursor(cursor);


        // new
        Fragment fragment = null;
        Class fragmentClass = null;

        fragmentClass = NoteFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }

        // Вставляем фрагмент, заменяя текущий фрагмент
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(container, fragment).commit();
        // end new



//        ListView listView = (ListView) findViewById(R.id.card_view);
//
//        ListAdapter adapter = new SimpleCursorAdapter(
//                this,
//                android.R.layout.two_line_list_item,
//                cursor,
//                new String[] { DatabaseManager.NAME_FIELD, DatabaseManager.DESCRIPTION_FIELD},
//                new int[] { android.R.id.text1, android.R.id.text2},
//                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//
//        listView.setAdapter(adapter);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
////
//
//
//        listView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener(){
//
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                        Intent intent = new Intent(getApplicationContext(), ShowNotes.class);
//                        intent.putExtra("id", id);
//                        startActivity(intent);
//                    }
//                });


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
