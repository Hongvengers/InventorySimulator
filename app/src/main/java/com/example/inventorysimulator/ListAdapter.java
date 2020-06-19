package com.example.inventorysimulator;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Items> itemList;
    private ViewHolder viewHolder;

    public ListAdapter(Context c, ArrayList<Items> array){
        inflater = LayoutInflater.from(c);
        itemList = array;
    }

//    private ImageView   itemIcon;
//    private TextView    itemName, itemDescription;
//
//    private ArrayList<Items> item_list = new ArrayList<Items>();



    public ListAdapter(){ }

    @Override
    public int getCount() { //이미지셋에 있는 아이템의 수를 반환함(그리드뷰는 아이템의 수에 해당하는 행렬을 준비)
        return itemList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //주어진 위치(position)에 출력할 이미지를 반환
        View v = convertView;

        if(v == null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.list, null);
            viewHolder.itemCode = v.findViewById(R.id.itemIcon);
            viewHolder.itemName = v.findViewById(R.id.itemName);
            viewHolder.itemDescription = v.findViewById(R.id.itemDescription);
            v.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.itemCode.setText(itemList.get(position).itemCode);
        viewHolder.itemName.setText(itemList.get(position).name);
        viewHolder.itemDescription.setText(itemList.get(position).description);

        return v;
//        final int pos = position;
//        final Context context = parent.getContext();
//
//        if (convertView == null){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.list,parent,false);
//        }
//
//        itemName = convertView.findViewById(R.id.itemName);
//        itemDescription = convertView.findViewById(R.id.itemDescription);
//        itemIcon = convertView.findViewById(R.id.itemIcon);
//
//        Items items = item_list.get(position);
//
//        itemName.setText(items.getTitle());
//        itemIcon.setImageResource(items.getIcon());
//        itemDescription.setText(items.getContent());
//
//        return convertView;
    }

    public void setArrayList(ArrayList<Items> arrays){
        this.itemList = arrays;
    }

    public ArrayList<Items> getArrayList(){
        return itemList;
    }

//    public void addItem(String title, int icon, String content){
//        Items items = new Items();
//
//        items.setTitle(title);
//        items.setIcon(icon);
//        items.setContent(content);
//
//        item_list.add(items);
//    }

    class ViewHolder{
        TextView itemCode;
        TextView itemName;
        TextView itemDescription;
    }
}
