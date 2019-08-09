package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DatabaseSampleActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_sample);

        Button createDbBtn = (Button)findViewById(R.id.createDbBtn);
        createDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 클릭되면 Database를 생성하고 Table을 만듬
                // Sqlite Database를 사용하기 쉽도록 도와주는 Helper class가 제공
                // 이 Helper class를 직접 이용하는게 아니라 상속받아서
                // 클래스를 작성한 후 사용자 정의 클래스의 객체를 이용
                // MySqliteHelper class를 작성해보자
                MySqliteHelper helper = new MySqliteHelper(DatabaseSampleActivity.this);

                // helper를 통해서 Database에 대한 Handle을 얻어올 수 있음
                db = helper.getWritableDatabase();
            }
        });

        Button selectDbBtn = (Button)findViewById(R.id.selectDbBtn);
        selectDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Database handle을 이용해서 Database처리가 가능
                // rawQuery() : select계열의 SQL문을 실행할 때 사용
                // Cursor의 역할은 JDBC의 ResultSet의 역할을 수행
                Cursor c = db.rawQuery("SELECT * FROM MEMBER", null);
                String result = "";

                while (c.moveToNext()){
                    result += c.getString(0);
                    result += ", ";
                    result += c.getInt(1);
                    result += "\n";
                }
                // 이렇게 데이터를 다 얻어오면 해당 결과를 TextView에 출력
                TextView tv = (TextView)findViewById(R.id.selectTv);
                tv.setText(result);
            }
        });
    }
}
