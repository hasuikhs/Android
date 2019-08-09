package com.example.kakaobooksearch;

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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class KAKAOBookAdapter extends BaseAdapter {

    private ArrayList<KAKAOBooKVO> list = new ArrayList<KAKAOBooKVO>();
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

        if(view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item,viewGroup,false);
        }

        ImageView bookimg = view.findViewById(R.id.bookImg);
        TextView author = view.findViewById(R.id.authorTextView);
        TextView title = view.findViewById(R.id.titleTextView);

        KAKAOBooKVO vo = list.get(i);

        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(vo.getThumbnailImg(),
                    0,vo.getThumbnailImg().length);
            bookimg.setImageBitmap(bmp);

            author.setText(vo.getAuthors().toString());
            title.setText(vo.getTitle());

        } catch(Exception e) {
            Log.i("KAKAOBOOKLog",e.toString());
        }
        return view;
    }

    public void addList(KAKAOBooKVO vo) {
        list.add(vo);
    }
}
