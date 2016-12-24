package ru.anstag.app.database2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    long contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //anstag
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowActivity.this, AddActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // end anstag


        Bundle extras = getIntent().getExtras();

        if(extras != null){
            contactId = extras.getLong("id");
            DatabaseManager dbMgr = new DatabaseManager(this);
            Contact contact = dbMgr.getContact(contactId);

            if(contact != null){
                ((TextView) findViewById(R.id.firstName)).setText(contact.getFirstName());
                ((TextView) findViewById(R.id.lastName)).setText(contact.getLastName());
                ((TextView) findViewById(R.id.phone)).setText(contact.getPhone());
                ((TextView) findViewById(R.id.email)).setText(contact.getEmail());
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
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                DatabaseManager dbMgr = new DatabaseManager(getApplicationContext());
                                dbMgr.deleteContact(contactId);
                                dialog.dismiss();
                                finish();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                dialog.dismiss();
                            }
                        })
                .create()
                .show();
    }
}
