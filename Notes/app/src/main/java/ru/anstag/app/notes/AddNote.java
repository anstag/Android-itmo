package ru.anstag.app.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddNote.this, AddNote.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void cancel(View view) {
        finish();
    }

    public void addNotes(View view){
        DatabaseManager dbMgr = new DatabaseManager(this);

        String name = ((TextView) findViewById(R.id.name)).getText().toString();
        String description = ((TextView) findViewById(R.id.description)).getText().toString();
        //String date =

        Notes notes = new Notes(name, description);
        dbMgr.addNotes(notes);
        finish();
    }

}
