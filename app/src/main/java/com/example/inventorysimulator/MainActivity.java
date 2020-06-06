package com.example.inventorysimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton masterBoxExit, inventoryExit;
    GridView    masterBoxGrid, inventoryGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int displayWidth = size.x;
        int displayHeight = size.y;

        GridView masterBoxGrid = findViewById(R.id.masterBoxGrid);
        GridView inventoryGrid = findViewById(R.id.inventoryGrid);

        ImageAdapter adapter = new ImageAdapter(this, displayWidth);
        masterBoxGrid.setAdapter(adapter);

        masterBoxGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), position + "클릭함", Toast.LENGTH_SHORT).show();
            }
        });

        inventoryGrid.setAdapter(adapter);

        inventoryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), position + "클릭함", Toast.LENGTH_SHORT).show();
            }
        });

        
    }
}
