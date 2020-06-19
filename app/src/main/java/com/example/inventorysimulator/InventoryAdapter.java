package com.example.inventorysimulator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class InventoryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<InventoryInfo> inventory;
    private ViewHolder viewHolder;

    public InventoryAdapter(Context c, ArrayList<InventoryInfo> array){
        inflater = LayoutInflater.from(c);
        inventory = array;
    }

    public InventoryAdapter(){}

    @Override
    public int getCount() {
        return inventory.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

        viewHolder.itemCode.setText(inventory.get(position).itemCode);
        viewHolder.itemName.setText(inventory.get(position).name);
        viewHolder.itemDescription.setText(inventory.get(position).description);

        return v;
    }

    public ArrayList<InventoryInfo> getArrayList(){
        return inventory;
    }

    class ViewHolder{
        TextView itemCode;
        TextView itemName;
        TextView itemDescription;
    }
}
