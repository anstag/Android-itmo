package ru.anstag.app.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShowNotes extends AppCompatActivity {

    long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowNotes.this, ShowNotes.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            noteId = extras.getLong("id");
            DatabaseManager dbMgr = new DatabaseManager(this);
            Notes notes = dbMgr.getNotes(noteId);

            if(notes != null){
                ((TextView) findViewById(R.id.name)).setText(notes.getName());
                ((TextView) findViewById(R.id.description)).setText(notes.getDescription());
                //((TextView) findViewById(R.id.date)).setText(notes.getDate());
            } else {
                Log.d("db", "contact null");
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_delete:
                deleteContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteContact() {
        new AlertDialog.Builder(this)
                .setTitle("Please confirm")
                .setMessage("Удалить заметку?")
                .setPositiveButton("Да",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                DatabaseManager dbMgr = new DatabaseManager(getApplicationContext());
                                dbMgr.deleteNotes(noteId);
                                dialog.dismiss();
                                finish();
                            }
                        })
                .setNegativeButton("Нет",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                dialog.dismiss();
                            }
                        })
                .create()
                .show();
    }

}
