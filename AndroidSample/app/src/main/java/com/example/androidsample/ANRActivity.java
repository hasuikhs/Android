package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

class MySum implements Runnable{

    private TextView tv;

    MySum(TextView tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        // Thread가 실행이 되면 수행되는 코드를 여기에 작성
        long sum = 0;
        for(long i = 0; i < 100000000L; i++){
            sum += i;
        }
        tv.setText("총합은 : " + sum);
    }
}

public class ANRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);

        final TextView tv = (TextView)findViewById(R.id.countTv);
        Button countBtn = (Button)findViewById(R.id.countBtn);
        Button toastBtn = (Button)findViewById(R.id.ToastBtn);

        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Thread를 파생시켜야 함
                MySum mySum = new MySum(tv); // Runnable interface를 구현한 객체

                Thread t = new Thread(mySum); // Thread를 생성

                t.start(); // non-blocking method
                           // 새로운 실행 흐름 생성 가능
//                long sum = 0;
//                for(long i = 0; i < 1000000000L; i++){
//                    sum += i;
//                }
//                tv.setText("총합은 : " + sum);
            }
        });
        toastBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(ANRActivity.this,"Toast가 실행", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
