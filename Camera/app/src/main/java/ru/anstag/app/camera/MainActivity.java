package ru.anstag.app.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    File pictureDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraDemo");
    private static final String FILE_NAME = "image01.jpg";

    private Uri fileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if ( !pictureDir.exists() ) {
            pictureDir.mkdirs();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_camera:
                showCamera();
                return true;

            case R.id.action_email:
                emailPicture();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }



    private void showCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File image = new File(pictureDir, FILE_NAME);
        fileUri = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // проверка существования камеры на устройстве
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE ) {

            if ( resultCode == RESULT_OK) {

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                File image = new File(pictureDir, FILE_NAME);
                fileUri = Uri.fromFile(image);
                imageView.setImageURI(fileUri);

                Toast.makeText(this, "[OK]!", Toast.LENGTH_LONG).show();

            } else if (requestCode == RESULT_CANCELED) {

                Toast.makeText(this, "Action canceled", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(this, "ERROR!", Toast.LENGTH_LONG).show();

            }

        }
    }





    private void emailPicture() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setType("application/image");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"info@anstag.ru"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "New photo");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From my App");
        emailIntent.putExtra(Intent.EXTRA_STREAM, fileUri);

        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }










}
