package com.example.androidsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class SearchMovieRankRunnable implements Runnable{
    private String keyword;
    private Handler handler;

    SearchMovieRankRunnable(Handler handler, String keywqrd){
        this.handler = handler;
        this.keyword = keywqrd;
    }

    @Override
    public void run() {
        String url ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=" + keyword;

        try {
            URL urlObj = new URL(url);
            HttpURLConnection con =(HttpURLConnection)urlObj.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String input = "";
            StringBuffer sb = new StringBuffer();

            while ((input = br.readLine()) != null){
                sb.append(input);
            }

            // JSON 문자열을 String[] 형태로변환하는 코드
            ObjectMapper mapper = new ObjectMapper();

            String[] resultArr = mapper.readValue(sb.toString(), String[].class);

            Bundle bundle = new Bundle();
            bundle.putStringArray("MOVIEARRAY", resultArr);

            Message msg = new Message();
            msg.setData(bundle);

            handler.sendMessage(msg);
        }catch (Exception e){
            Log.i("Error", e.toString());
        }
    }
}



public class BoxOfficeRankActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office_rank);

        Button searchDateBtn = (Button)findViewById(R.id.searchRankBtn);
        final EditText dateEt = (EditText)findViewById(R.id.dateEt);
        final ListView movieList= (ListView)findViewById(R.id.movieList);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle bundle =msg.getData();
                String[] result = bundle.getStringArray("MOVIEARRAY");

                ArrayAdapter adapter = new ArrayAdapter(BoxOfficeRankActivity.this,
                                                        android.R.layout.simple_list_item_1, result);
                movieList.setAdapter(adapter);
            }
        };

        searchDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchMovieRankRunnable runnable = new SearchMovieRankRunnable(handler, dateEt.getText().toString());
                Thread t = new Thread(runnable);

                t.start();
            }
        });
    }
}
