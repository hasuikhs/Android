package com.example.androidsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

// ListView안의 내용을 그리는 역할을 하는 adapter
public class CustomListViewAdapter extends BaseAdapter {

    private List<BookVO> list = new ArrayList<>();

    Bitmap bitmap;
    // 반드시 overriding을 해야하는 method가 존재

    public void addItem(BookVO vo){
        list.add(vo);
    }

    @Override
    public int getCount() {
        // 총 몇개의 component를 그려야 하는지를 return
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        // 몇번째 데이터를 화면에 출력할지를 결정
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final Context context = viewGroup.getContext();

        // 출력할 View 객체를 생성
        if(view == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 생성된 view 객체에 XML Layout을 설정
            view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        // 출력할 View Component의 refernce를 획득
        ImageView iv = (ImageView)view.findViewById(R.id.customIv);
        TextView tv1 = (TextView)view.findViewById(R.id.customTv1);
        TextView tv2 = (TextView)view.findViewById(R.id.customTv2);

        BookVO vo = list.get(i);    // 화면에 출력할 데이터를 호출


//        // 객체에서의 이미지 주소를 파싱
//        final String imgurl = vo.getBimgurl();
//
//        // 네트워크 관련된 작업을 진행할 때에는 별도의 Thread를 생성 후 작업
//        Thread mThread = new Thread(){
//            @Override
//            public void run() {
//                try{
//                    URL url = new URL(imgurl);
//                    // web에서 이미지를 가져온뒤
//                    // ImageView에 지정할 Bitmap 생성
//                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                    conn.setDoInput(true); // 서버로부터 응답 수신
//                    conn.connect();
//                    InputStream is = conn.getInputStream(); // InputStream 값 가져오기
//                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환
//
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        mThread.start(); // Thread 실행
//
//        try{
//            // 메인 Thread는 별도의 작업 Thread가 작업을 완료할 때까지 대기
//            // join()을 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 대기
//            mThread.join();
//
//            // 작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
//            // UI작업을 할 수 있는 메인 Thread에서 ImageView에 이미지를 지정정
//           iv.setImageBitmap(bitmap);
//        } catch(Exception e){
//            e.printStackTrace();
//        }
        iv.setImageDrawable(vo.getDrawable());
        tv1.setText(vo.getBtitle());
        tv2.setText(vo.getBauthor());

        return view;
    }
}
