package com.example.valenzaceramic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class Tiles_List_Activity extends AppCompatActivity {
    /*RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;

*/
    //Firebase url;
    public Tile public_tile;
    static int id;
    int id_list[];
    TextView nameTextView, temp;
    ImageView tileImageView;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    private ArrayList<Tile> TilesList;
    private ArrayList<ImageView> ImagesList;
    private LinearLayout rootview;
    private  ImageView i1,i2,i3,i4;
    String lastTile;
    String Tile_name[], Tile_data[];
    static int i = 0, j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles__list_);
        Firebase.setAndroidContext(this);
        // TileListView = (ListView)findViewById(R.id.list_view);
        Tile_data = new String[6];
        rootview = (LinearLayout) findViewById(R.id.root_view);
        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://valenzaceramic-8db2b.appspot.com/");
        mDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://valenzaceramic-8db2b.firebaseio.com/Products/Tiles/FloorTiles/");


    }

    @Override
    protected void onStart() {
        super.onStart();
        rootview.removeAllViews();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    Tile t = (Tile) d.getValue(Tile.class);

                    String tilename = (String) d.getKey();
                    // Tile_Names[i++] = tilename;
                    Tile_data[0] = tilename;
                    lastTile = tilename;
                    //        TilesList.add(t);

                    String Applications = t.getApplications();
                    Tile_data[1] = Applications;

                    String Dimension = t.getDimension();
                    Tile_data[2] = Dimension;
                    String Finish = t.getFinish();
                    Tile_data[3] = Finish;
                    String Image = t.getImage();
                    Tile_data[4] = Image;
                    String Price = t.getPrice();
                    Tile_data[5] = Price;
                    temp = new TextView(Tiles_List_Activity.this);
                    temp.setText("Name " + tilename + "Price : " + Price);

                   ImageView temp2 = new ImageView(Tiles_List_Activity.this);

                    rootview.addView(temp);
                    rootview.addView(temp2);

                    //ImagesList.add(temp2);
                    Picasso.get().load(Image).resize(500, 500).into(temp2);


                        temp2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Tiles_List_Activity.this, OrderActivity.class);

                                intent.putExtra("Tile_N", Tile_data[0]);
                                intent.putExtra("Tile_A", Tile_data[1]);
                                intent.putExtra("Tile_D", Tile_data[2]);
                                intent.putExtra("Tile_F", Tile_data[3]);
                                intent.putExtra("Tile_I", Tile_data[4]);
                                intent.putExtra("Tile_P", Tile_data[5]);

                                startActivity(intent);



                            }
                        });

                    i++;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
 /*  if(i == 0) {

                                intent.putExtra("Tile_N", Tile_data[0][0]);
                                intent.putExtra("Tile_A", Tile_data[0][1]);
                                intent.putExtra("Tile_D", Tile_data[0][2]);
                                intent.putExtra("Tile_F", Tile_data[0][3]);
                                intent.putExtra("Tile_I", Tile_data[0][4]);
                                intent.putExtra("Tile_P", Tile_data[0][5]);
                            }
                            else if(i == 1)
                            {

                                intent.putExtra("Tile_N", Tile_data[1][0]);
                                intent.putExtra("Tile_A", Tile_data[1][1]);
                                intent.putExtra("Tile_D", Tile_data[1][2]);
                                intent.putExtra("Tile_F", Tile_data[1][3]);
                                intent.putExtra("Tile_I", Tile_data[1][4]);
                                intent.putExtra("Tile_P", Tile_data[1][5]);
                            }

                            else if(i == 2)
                            {

                                intent.putExtra("Tile_N", Tile_data[2][0]);
                                intent.putExtra("Tile_A", Tile_data[2][1]);
                                intent.putExtra("Tile_D", Tile_data[2][2]);
                                intent.putExtra("Tile_F", Tile_data[2][3]);
                                intent.putExtra("Tile_I", Tile_data[2][4]);
                                intent.putExtra("Tile_P", Tile_data[2][5]);
                            }
                            else if(i == 3)
                            {

                                intent.putExtra("Tile_N", Tile_data[3][0]);
                                intent.putExtra("Tile_A", Tile_data[3][1]);
                                intent.putExtra("Tile_D", Tile_data[3][2]);
                                intent.putExtra("Tile_F", Tile_data[3][3]);
                                intent.putExtra("Tile_I", Tile_data[3][4]);
                                intent.putExtra("Tile_P", Tile_data[3][5]);
                            }
                            else if(i==4)
                            {

                                intent.putExtra("Tile_N", Tile_data[4][0]);
                                intent.putExtra("Tile_A", Tile_data[4][1]);
                                intent.putExtra("Tile_D", Tile_data[4][2]);
                                intent.putExtra("Tile_F", Tile_data[4][3]);
                                intent.putExtra("Tile_I", Tile_data[4][4]);
                                intent.putExtra("Tile_P", Tile_data[4][5]);
                            }
                            else if(i == 5)
                            {

                                intent.putExtra("Tile_N", Tile_data[5][0]);
                                intent.putExtra("Tile_A", Tile_data[5][1]);
                                intent.putExtra("Tile_D", Tile_data[5][2]);
                                intent.putExtra("Tile_F", Tile_data[5][3]);
                                intent.putExtra("Tile_I", Tile_data[5][4]);
                                intent.putExtra("Tile_P", Tile_data[5][5]);
                            }
                            else if(i == 6)
                            {

                                intent.putExtra("Tile_N", Tile_data[6][0]);
                                intent.putExtra("Tile_A", Tile_data[6][1]);
                                intent.putExtra("Tile_D", Tile_data[6][2]);
                                intent.putExtra("Tile_F", Tile_data[6][3]);
                                intent.putExtra("Tile_I", Tile_data[6][4]);
                                intent.putExtra("Tile_P", Tile_data[6][5]);
                            }
                            else  if(i == 7)
                            {

                                intent.putExtra("Tile_N", Tile_data[7][0]);
                                intent.putExtra("Tile_A", Tile_data[7][1]);
                                intent.putExtra("Tile_D", Tile_data[7][2]);
                                intent.putExtra("Tile_F", Tile_data[7][3]);
                                intent.putExtra("Tile_I", Tile_data[7][4]);
                                intent.putExtra("Tile_P", Tile_data[7][5]);
                            }
                            else if(i == 8)
                            {

                                intent.putExtra("Tile_N", Tile_data[8][0]);
                                intent.putExtra("Tile_A", Tile_data[8][1]);
                                intent.putExtra("Tile_D", Tile_data[8][2]);
                                intent.putExtra("Tile_F", Tile_data[8][3]);
                                intent.putExtra("Tile_I", Tile_data[8][4]);
                                intent.putExtra("Tile_P", Tile_data[8][5]);
                            }
                            else  if(i == 9)
                            {

                                intent.putExtra("Tile_N", Tile_data[9][0]);
                                intent.putExtra("Tile_A", Tile_data[9][1]);
                                intent.putExtra("Tile_D", Tile_data[9][2]);
                                intent.putExtra("Tile_F", Tile_data[9][3]);
                                intent.putExtra("Tile_I", Tile_data[9][4]);
                                intent.putExtra("Tile_P", Tile_data[9][5]);
                            }
                            else
                            {

                                intent.putExtra("Tile_N", Tile_data[10][0]);
                                intent.putExtra("Tile_A", Tile_data[10][1]);
                                intent.putExtra("Tile_D", Tile_data[10][2]);
                                intent.putExtra("Tile_F", Tile_data[10][3]);
                                intent.putExtra("Tile_I", Tile_data[10][4]);
                                intent.putExtra("Tile_P", Tile_data[10][5]);
                            }
*/
