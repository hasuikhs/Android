package com.example.androidsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


class MovieSearchRunnable implements Runnable{
    private Handler handler;
    private String keyword;

    MovieSearchRunnable(Handler handler, String keyword){
        this.handler = handler;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=" + keyword;

        try{
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String input ="";
            StringBuffer sb = new StringBuffer();

            while ((input = br.readLine()) != null){
                sb.append(input);
            }
            br.close();

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(sb.toString(),
                    new TypeReference<Map<String, Object>>() {});
            Object obj = map.get("boxOfficeResult");
            String resultJsonData = mapper.writeValueAsString(obj);
            Log.i("resultJsonData", resultJsonData);

            MovieVO[] resultArr = mapper.readValue(sb.toString(), MovieVO[].class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("MOVIES", resultArr);
            Message msg = new Message();
            msg.setData(bundle);

            handler.sendMessage(msg);
        } catch (Exception e){
            Log.i("movieListView", e.toString());
        }
    }
}

public class BoxOfficeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);

        final Button searchBoxOfficeBtn = (Button)findViewById(R.id.searchMovieBtn);
        final EditText boxOfficeEt = (EditText)findViewById(R.id.searchMovieEt);
        final ListView movieLv = (ListView)findViewById(R.id.movieLv);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                Bundle bundle = msg.getData();
                MovieVO[] list = (MovieVO[])bundle.getSerializable("MOVIES");

                MovieListViewAdapter adapter = new MovieListViewAdapter();

                movieLv.setAdapter(adapter);

                for (MovieVO vo : list){
                    adapter.addItem(vo);
                }
            }
        };

        searchBoxOfficeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MovieSearchRunnable runnable =
                        new MovieSearchRunnable(handler, boxOfficeEt.getText().toString());
                Thread t = new Thread(runnable);
                t.start();
            }
        });

    }
}
