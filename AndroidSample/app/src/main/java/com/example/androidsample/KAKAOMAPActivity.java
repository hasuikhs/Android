package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
/* 카카오 맵을 이용하기 위해선 manifast xml 파일에 설정 추가 */

public class KAKAOMAPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakaomap);

        MapView map = new MapView(this);

        ViewGroup group = (ViewGroup)findViewById(R.id.mapll);

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(37.501315, 127.039583);
        map.setMapCenterPoint(mapPoint, true);
        group.addView(map);
    }
}
