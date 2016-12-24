package ru.anstag.app.http;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.anstag.app.http.R;

public class ProgressFragment extends Fragment {

    TextView contentView;
    String contentText = null;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public ProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        contentView = (TextView) view.findViewById(R.id.content);

        if(contentText != null) {
            contentView.setText(contentText);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(contentText == null) {
            new ProcessTask().execute();
        }
    }

    class ProcessTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... path) {
            String content;
            try{
                content = getContent("https://vk.com/anstag_gp");
            } catch (IOException ex){
                content =  ex.getMessage();
            }

            return content;
        }

        protected void onProgressUpdate(Void... items) {
        }

        @Override
        protected void onPostExecute(String content) {
            contentText = content;
            contentView.setText(content);
            Toast.makeText(getActivity(), "Данные загружены", Toast.LENGTH_SHORT).show();
        }
    }



    private String getContent(String path) throws IOException {
        BufferedReader reader = null;

        try {
            URL url = new URL(path);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setReadTimeout(10000);
            c.connect();

            reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder buf = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null) {
                buf.append(line + "\n");
            }

            return (buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }

    }



}
