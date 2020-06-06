package com.example.inventorysimulator;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private int displayWidth;  //화면크기
    private int size;          //이미지크기

    private Context context;    //출력될 이미지 데이터셋(res/drawable)

    int[] itemImages = {R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground};
    private int[] images = itemImages;

    public ImageAdapter(Context c, int displayWidth){
        context = c;
        this.displayWidth = displayWidth;
        size = displayWidth / 6;

        System.out.println("size=" + size);
    }

    @Override
    public int getCount() { //이미지셋에 있는 아이템의 수를 반환함(그리드뷰는 아이템의 수에 해당하는 행렬을 준비)
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //주어진 위치(position)에 출력할 이미지를 반환
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

        }
        else{
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(images[position]);
        return imageView;
    }
}
