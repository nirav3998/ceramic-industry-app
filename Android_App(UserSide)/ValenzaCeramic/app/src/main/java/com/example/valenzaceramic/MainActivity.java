package com.example.valenzaceramic;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        TextView tf_tiles,tf_marbles,tf_mosaico,tf_custom_order;
        //private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);






        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        tf_tiles= (TextView)findViewById(R.id.tiles);
        tf_marbles=(TextView)findViewById(R.id.Marbles);
        tf_mosaico=(TextView)findViewById(R.id.mosaico);
        tf_custom_order = (TextView) findViewById(R.id.custom_order);

        tf_tiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiles_onclick();
            }
        });

        tf_marbles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marbles_onclick();
            }
        });
        tf_mosaico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mosaico_onclick();
            }
        });
        tf_custom_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom_order_onclick();
            }
        });



//add here navigation

    }
    public void tiles_onclick()
    {
        Intent intent = new Intent(this,TIles_catalog.class);
        startActivity(intent);

    }
    public void marbles_onclick()
    {
        Intent intent = new Intent(this,Marbles_catalog.class);
        startActivity(intent);

    }
    public void mosaico_onclick()
    {
        Intent intent = new Intent(this, Mosaico_List_Activity.class);
        startActivity(intent);

    }

    public void custom_order_onclick()
    {
        Intent intent = new Intent(this, custum_tile_activity.class);
        startActivity(intent);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
