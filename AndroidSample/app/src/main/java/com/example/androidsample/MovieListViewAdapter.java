package com.example.androidsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieListViewAdapter extends BaseAdapter {
    private List<MovieVO> list = new ArrayList<>();

    public void addItem(MovieVO vo){
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
            view = inflater.inflate(R.layout.movielist_item, viewGroup, false);
        }

        TextView tv1 = (TextView)view.findViewById(R.id.rankTv);
        TextView tv2 = (TextView)view.findViewById(R.id.movieTitleTv);

        MovieVO vo = list.get(i);

        tv1.setText(vo.getRank());
        tv2.setText(vo.getMovieNm());

        return view;
    }
}
