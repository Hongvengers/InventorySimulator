package com.example.inventorysimulator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton masterBoxExit, inventoryExit;
    private ListView    masterBoxList, inventoryList;
    private ListAdapter adapter;
    private InventoryAdapter    inAdapter;
    private Items       mItems;
    private InventoryInfo mInventory;
    private ArrayList<Items> itemsArrayList;
    private ArrayList<InventoryInfo>    inventoryArrayList;
    private Cursor      cursor, cursor2;
    private DBHelper    helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        helper.getWritableDatabase();

        itemsArrayList = new ArrayList<Items>();
        inventoryArrayList = new ArrayList<InventoryInfo>();

        doWhileCursorToArray();
        doWhileCursorToInven();

        adapter = new ListAdapter(this, itemsArrayList);
        masterBoxList = findViewById(R.id.masterBoxList);
        masterBoxList.setAdapter(adapter);
        masterBoxList.setOnItemClickListener(new ListViewItemClickListener());

        inAdapter = new InventoryAdapter(this, inventoryArrayList);
        inventoryList = findViewById(R.id.inventoryList);
        inventoryList.setAdapter(inAdapter);
        inventoryList.setOnItemClickListener(new InventoryClickListener());

    }

    private class ListViewItemClickListener implements AdapterView.OnItemClickListener{
//        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//            cursor = db.rawQuery("SELECT Name FROM items WHERE itemCode = '" + position + "';", null);
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());

            alertDlg.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    try {
                        String code = itemsArrayList.get(position).getItemCode();
                        String name = itemsArrayList.get(position).getName();
                        String description = itemsArrayList.get(position).getDescription();
                        transport(code, name, description);
                        doWhileCursorToInven();
                    }
                    catch (SQLiteException e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "아이템을 중복으로 가질 수 없습니다!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            alertDlg.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });

            String name = itemsArrayList.get(position).getName();
            String code = itemsArrayList.get(position).getItemCode();
            String description = itemsArrayList.get(position).getDescription();

            alertDlg.setTitle(name);
            alertDlg.setMessage(description + "\n아이템코드: " + code + "\n이 아이템을 인벤토리로 옮기시겠습니까?");
            alertDlg.show();
        }
    }

    private class InventoryClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());

            alertDlg.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                        String code = inventoryArrayList.get(position).getItemCode();
//                        String name = inventoryArrayList.get(position).getName();
//                        String description = inventoryArrayList.get(position).getDescription();
                        delete(code);
                }
            });

            alertDlg.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });

            String name = inventoryArrayList.get(position).getName();
            String code = inventoryArrayList.get(position).getItemCode();
            String description = inventoryArrayList.get(position).getDescription();

            alertDlg.setTitle(name);
            alertDlg.setMessage(description + "\n아이템코드: " + code + "\n이 아이템을 인벤토리에서 삭제하시겠습니까?");
            alertDlg.show();
        }
    }

    private void transport(String code, String name, String description){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO inventory VALUES(null, '" + code + "', '" + name + "', '" + description + "');");
//        doWhileCursorToInven();
        select();

    }

    private void select(){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM inventory;", null);
        c.moveToFirst();
        String[] from = {"itemCode", "Name", "Description"};
        int[] to= {R.id.itemIcon, R.id.itemName, R.id.itemDescription};

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(inventoryList.getContext(), R.layout.list, c, from, to);
        inventoryList.setAdapter(adapter);

        db.close();
    }

    private void delete(String code){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM inventory WHERE itemCode = '" + code + "';");
        select();
    }

    private void doWhileCursorToArray(){
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = null;
        cursor = db.rawQuery("SELECT * FROM items;", null);
        cursor.moveToFirst();
        do{
            mItems = new Items(
                cursor.getString(cursor.getColumnIndex("itemCode")),
                cursor.getString(cursor.getColumnIndex("Name")),
                cursor.getString(cursor.getColumnIndex("Description"))
        );

            itemsArrayList.add(mItems);

        }
        while (cursor.moveToNext());
        cursor.close();
    }

    private void doWhileCursorToInven(){
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = null;
        cursor = db.rawQuery("SELECT * FROM inventory;", null);
        cursor.moveToFirst();
        do{
            mInventory = new InventoryInfo(
                    cursor.getString(cursor.getColumnIndex("itemCode")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getString(cursor.getColumnIndex("Description"))
            );

            inventoryArrayList.add(mInventory);

        }
        while (cursor.moveToNext());

    }

    class DBHelper extends SQLiteOpenHelper {
        public SQLiteDatabase mDB;
        public DBHelper(Context context) {
            super(context, "InventoryDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE items(_id INTEGER PRIMARY KEY" + " AUTOINCREMENT, itemCode VARCHAR(8) UNIQUE, Name CHAR(20), Description CHAR(50));");
            db.execSQL("CREATE TABLE inventory(_id INTEGER PRIMARY KEY" + " AUTOINCREMENT, itemCode VARCHAR(8) UNIQUE, Name CHAR(20), Description CHAR(50));");
            db.execSQL("INSERT INTO items VALUES(null, '0', '아이템1', '아이템1 입니다')");
            db.execSQL("INSERT INTO items VALUES(null, '1', '아이템2', '아이템2 입니다')");
            db.execSQL("INSERT INTO items VALUES(null, '2', '아이템3', '아이템3 입니다')");
            db.execSQL("INSERT INTO inventory VALUES(null, '1004', '기본아이템', '기본아이템 입니다')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS items");
            onCreate(db);
        }
    }
}
