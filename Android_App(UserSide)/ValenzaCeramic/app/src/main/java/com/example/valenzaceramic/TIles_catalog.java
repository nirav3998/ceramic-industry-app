package com.example.valenzaceramic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TIles_catalog extends AppCompatActivity {
    private TextView tv_Floor_Tiles ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles_catalog);
        tv_Floor_Tiles =(TextView)findViewById(R.id.floor_tiles);

        tv_Floor_Tiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoTilesListActivity();
            }
        });

    }
    private void gotoTilesListActivity()
    {
        Intent intent = new Intent(this,Tiles_List_Activity.class);
        startActivity(intent);
    }
}
