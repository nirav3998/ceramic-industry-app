package com.example.valenzaceramic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class OrderActivity extends AppCompatActivity {

    TextView A,D,F,P,N;
    ImageView I;
    Button makeOrderButton;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Firebase.setAndroidContext(this);
        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://valenzaceramic-8db2b.appspot.com/");
        mDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://valenzaceramic-8db2b.firebaseio.com/orders/");

        Intent i = getIntent();
        makeOrderButton = (Button)findViewById(R.id.mae_order_button);
        makeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        N = (TextView)findViewById(R.id.order_tile_Name);
        A = (TextView)findViewById(R.id.order_tile_Applications);
        D = (TextView)findViewById(R.id.order_tile_Dimensions);
        F = (TextView)findViewById(R.id.order_tile_Finish);
        I = (ImageView)findViewById(R.id.order_tile_image_view);
        P = (TextView)findViewById(R.id.order_tile_price);

        final  String name =i.getStringExtra("Tile_N");
        N.setText( name);
        A.setText("Applications :" + i.getStringExtra("Tile_A"));
        D.setText("Dimensions : " +i.getStringExtra("Tile_D"));
        F.setText("Finish :  " + i.getStringExtra("Tile_F"));
        Picasso.get().load(i.getStringExtra("Tile_I")).into(I);
        P.setText( "Price  :  " + i.getStringExtra("Tile_P") + "$");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mDatabaseRef.child("yjoshi").child("status").setValue("Pending");
                mDatabaseRef.child("yjoshi").child("Name").setValue(name);
                mDatabaseRef.child("yjoshi").child("No. Of Pieces").setValue("100");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    protected  void  onStart()
    {
        super.onStart();

    }

}
