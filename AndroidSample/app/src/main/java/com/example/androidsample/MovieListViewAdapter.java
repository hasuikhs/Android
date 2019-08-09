package com.example.androidsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.movielist_item, viewGroup, false);
        }

        TextView rankTv = (TextView)view.findViewById(R.id.rankTv);
        TextView movieNmTv = (TextView)view.findViewById(R.id.movieNmTv);

        MovieVO vo = list.get(i);

        rankTv.setText(vo.getRank());
        movieNmTv.setText(vo.getMovieNm());

        return view;
    }
}
