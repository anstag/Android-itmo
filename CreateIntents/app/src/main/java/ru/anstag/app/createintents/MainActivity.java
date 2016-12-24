package ru.anstag.app.createintents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCall = (Button) findViewById(R.id.btnCall);
        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnMail = (Button) findViewById(R.id.btnMail);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel: 981-74-88");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                if (isIntentSafe(callIntent))
                    startActivity(callIntent);
                else
                    Toast.makeText(getApplicationContext(), "У вас нет приложения для звонка", Toast.LENGTH_LONG).show();
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo: 37.422219, -122.083843?z=14");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                if (isIntentSafe(mapIntent))
                    startActivity(mapIntent);
                else
                    Toast.makeText(getApplicationContext(), "У вас нет карт", Toast.LENGTH_LONG).show();
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://anstag.ru/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

                if (isIntentSafe(webIntent))
                    startActivity(webIntent);
                else
                    Toast.makeText(getApplicationContext(), "У вас нет браузера", Toast.LENGTH_LONG).show();
            }
        });

        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "info@anstag.ru", null));

                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kolyansfr@yandex.ru, kolyanwork@gmail.com"});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Это тема сообщения(отправка через андроид)");
                mailIntent.putExtra(Intent.EXTRA_TEXT, "Это тело сообщения! SWAG");

                if (isIntentSafe(mailIntent))
                    startActivity(mailIntent);
                else
                    Toast.makeText(getApplicationContext(), "У вас нет приложения для отправки почты", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean isIntentSafe(Intent intent){
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }
}
