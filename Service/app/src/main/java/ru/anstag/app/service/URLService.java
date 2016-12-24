package ru.anstag.app.service;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;


public class URLService extends IntentService {

    // создание конструктора
    public URLService() {
        super("URLService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // получение данных интента из MainActivity
        String urls = intent.getStringExtra("urls");

        if (urls == null) {
            return;
        }

        // разделение строк на части, посредством StringTokenizer
        StringTokenizer tokenizer = new StringTokenizer(urls);

        int tokenCount = tokenizer.countTokens();
        int index = 0;

        String[] targets = new String[tokenCount];

        while (tokenizer.hasMoreTokens()) {
            targets[index++] = tokenizer.nextToken();
        }

        File saveDir = getFilesDir();

        // создание метода getPagesAndSave
        getPagesAndSave(saveDir, targets);

    }

    private void getPagesAndSave(File saveDir, String[] targets) {

        for (String target: targets) {
            URL url = null;

            try {
                url = new URL(target);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String fileName = target.replaceAll("/", "-").replaceAll(":", "-");

            // хранение данных в файловой системе
            File file = new File(saveDir, fileName);

            // класс Writer - символьный потоковый вывод
            PrintWriter writer = null;

            // буфер
            BufferedReader reader = null;

            try {
                writer = new PrintWriter(file);

                reader = new BufferedReader(new InputStreamReader(url.openStream()));

                String line;

                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }

            } catch (Exception e) {

            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception e) {

                    }
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {

                    }
                }
            }

        }
    }


}
