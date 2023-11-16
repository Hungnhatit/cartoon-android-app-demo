package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.json.databinding.ActivityMainBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    ArrayList<MyItem> arrayItem;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView=(ListView) findViewById(R.id.listviewName);
        arrayItem =new ArrayList<>();
        adapter=new MyAdapter(MainActivity.this, R.layout.item, arrayItem);
        myListView.setAdapter(adapter);
        new ReadJSON().execute("https://run.mocky.io/v3/780ea1c4-1870-48bb-aa01-e7b99c8d64e4");
    }
    private class ReadJSON extends AsyncTask<String, Void, String>
    {
        protected String doInBackground(String... strings)
        {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            try {
                JSONObject object=new JSONObject(s);

                JSONArray array= object.getJSONArray("movies");
                for(int i=0;i<array.length();i++){
                    JSONObject objectpr=array.getJSONObject(i);
                    String name=objectpr.getString("name");
                    String year=objectpr.getString("year");
                    String image = objectpr.getString("image");
                    arrayItem.add(new MyItem(image, name, year));
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
        }

}

