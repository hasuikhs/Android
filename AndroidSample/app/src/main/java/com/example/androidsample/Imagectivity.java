package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Imagectivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagectivity);

        Button imgBtn = (Button)findViewById(R.id.imgBtn);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView iv = (ImageView)findViewById(R.id.iv);
                iv.setImageResource(R.drawable.t);
            }
        });
    }
}
