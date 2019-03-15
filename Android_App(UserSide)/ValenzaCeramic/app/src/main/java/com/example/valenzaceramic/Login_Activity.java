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

public class Login_Activity extends AppCompatActivity {

    private Button mButtonLogin,mButttonRegister;
    private EditText mEdit_Text_Username,mEdit_text_Password;
    public String username,password;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mButttonRegister = (Button)findViewById(R.id.button_register);
        mEdit_text_Password = (EditText)findViewById(R.id.edit_text_password);
        mEdit_Text_Username = (EditText)findViewById(R.id.edit_text_username);

        Firebase.setAndroidContext(this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("userInfo");

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = mEdit_Text_Username.getText().toString();

                password = mEdit_text_Password.getText().toString();

               /* if((username != null)&& (password != null))*/if(true)
                {/*
                    //checking
                    mDatabaseRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String passFromDb = dataSnapshot.child(username).child("password").getValue(String.class);
                            if(passFromDb.equals(password))
                            {
                                gotoMainActivity();
                            }
                            else
                            {
                                Toast.makeText(Login_Activity.this,"Incorrect Passsword or Username",Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/
                    gotoMainActivity();

                }
                else
                {
                    Toast.makeText(Login_Activity.this,"Incorrect Passsword or Username",Toast.LENGTH_SHORT).show();

                }

            }
        });
        mButttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegisterActivity();
            }
        });
    }
    private void gotoMainActivity()
    {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    private void gotoRegisterActivity()
    {
        Intent intent = new Intent(this,Register_Activity.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        //System.exit(0);
    }

}
