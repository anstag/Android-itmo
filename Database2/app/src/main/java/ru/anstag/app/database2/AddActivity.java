package ru.anstag.app.database2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, AddActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void cancel(View view) {
        finish();
    }

    public void addContact(View view){
        DatabaseManager dbMgr = new DatabaseManager(this);

        String firstName = ((TextView) findViewById(R.id.firstName)).getText().toString();
        String lastName = ((TextView) findViewById(R.id.lastName)).getText().toString();
        String phone = ((TextView) findViewById(R.id.phone)).getText().toString();
        String email = ((TextView) findViewById(R.id.email)).getText().toString();

        Contact contact = new Contact(firstName, lastName, phone, email);
        dbMgr.addContact(contact);
        finish();
    }

}
