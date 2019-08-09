package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class BroadcastTestActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);

        Button registerBtn = (Button)findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Broadcast Receiver를 생성
                // 먼저 Broadcast receiver가 어떤 Broadcast를 청취 가능한지를
                // 나타내는 intent filter를 생성
                Log.i("onclick","11111");
                IntentFilter filter = new IntentFilter();
                filter.addAction("MY_BROADCAST");
                // 안드로이드 시스템에서 나오는 여러가지 정해져있는 Broadcast를 catch 가능

                // 안드로이드의 3번째 component인 broadcast receiver를 생성
                receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        // broadcast를 잡았을 때 처리해야 할 코드 작성
                        Log.i("dd","44444");
                        Toast.makeText(context, "신호 캐치", Toast.LENGTH_SHORT).show();

                        // Notification을 사용해 보자
                        // NotificationManager객체를 획득
                        NotificationManager nManager =
                                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                        // channelID와 channelName, Notification 중요도 설정
                        String channelId = "MY_CHANNEL";
                        String channelName = "MY_CHANNEL_NAME";
                        int important = NotificationManager.IMPORTANCE_HIGH;

                        // Oreo버전 이상에서는 channel 객체를 생성해서 설정해야 함
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                            NotificationChannel channel = new NotificationChannel(channelId, channelName, important);
                            nManager.createNotificationChannel(channel);
                        }

                        // Notification을 생성
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);

                        // Intent를 하나 생성 => 나중에 Notification을 클릭했을 때
                        // 화면에 Activity를 보여주기 위한 용도
                        Intent nIntent = new Intent(context, BroadcastTestActivity.class);
                        nIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        nIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        int requestID = 10;

                        // PendingIntent는 intent를 가지고 있는 intent
                        // Intent의 수행을 지연시키는 역할을 수행
                        PendingIntent pIntent = PendingIntent.getActivity(context, requestID, nIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                        // Notification 설정 부분
                        builder.setContentTitle("제목부분")
                                .setContentText("이곳은 내용이여")
                                .setAutoCancel(true)    // 터치했을떄 사라지도록 처리
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                .setSmallIcon(android.R.drawable.btn_star) // 별모양의 아이콘 표시
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.d))
                                .setContentIntent(pIntent);

                        // NotificationManager를 통해서 실제 Notification 실행
                        nManager.notify(0, builder.build());
                        Log.i("dd","050505");
                    }
                };
                // 등록작업 (리시버가 등록되어야지 신호 캐치 가능)
                registerReceiver(receiver, filter);
            }
        });

        final Button unregisterBtn =(Button)findViewById(R.id.unregisterBtn);
        unregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼이 클릭되면 receiver의 등록을 해제
                unregisterReceiver(receiver);
            }
        });

        Button sendSignalBtn = (Button)findViewById(R.id.sendSignalBtn);
        sendSignalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("onclick","22221");
                Intent i = new Intent();
                i.setAction("MY_BROADCAST");
                sendBroadcast(i);
            }
        });
    }
}
