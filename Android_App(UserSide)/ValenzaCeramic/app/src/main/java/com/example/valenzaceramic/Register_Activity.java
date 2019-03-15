package com.example.valenzaceramic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Register_Activity extends AppCompatActivity {

    private Button mRegister_final;
    private EditText mEdit_text_name,mEdit_text_username,mEdit_text_email,mEdit_text_contact_no,mEdit_text_password;
    private String name,username,password,email,contactNo;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        mRegister_final = (Button)findViewById(R.id.button_register_final) ;
        mEdit_text_name = (EditText)findViewById(R.id.edit_text_name_new);
        mEdit_text_username = (EditText)findViewById(R.id.edit_text_username_new);
        mEdit_text_password = (EditText)findViewById(R.id.edit_text_password_new);
        mEdit_text_email = (EditText)findViewById(R.id.edit_text_email);
        mEdit_text_contact_no = (EditText)findViewById(R.id.edit_text_contactNo);

        Firebase.setAndroidContext(this);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("userInfo");


        mRegister_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check and Update
                name = mEdit_text_name.getText().toString();
                username = mEdit_text_username.getText().toString();
                password = mEdit_text_password.getText().toString();
                email = mEdit_text_email.getText().toString();
                contactNo = mEdit_text_contact_no.getText().toString();

                if((name != null)&&(username != null) &&(password != null)&&(email != null) &&(contactNo != null))
                {
                    mDatabaseRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mDatabaseRef.child(username).child("contact").setValue(contactNo);
                            mDatabaseRef.child(username).child("email").setValue(email);
                            mDatabaseRef.child(username).child("name").setValue(name);
                            mDatabaseRef.child(username).child("password").setValue(password);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    gotoLoginActivity();
                }
                else
                {
                    Toast.makeText(Register_Activity.this,"Fill All Fields",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void gotoLoginActivity()
    {
        Intent intent = new Intent(this,Login_Activity.class);
        startActivity(intent);

    }
}
