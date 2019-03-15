package com.example.valenzaceramic;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.widget.Toast.LENGTH_SHORT;

public class custum_tile_activity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private StorageTask mUploadTask;

    private Uri mImageUri;

    private StorageReference mStorageRef;

    private DatabaseReference mDatabaseRef;
    private FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custum_tile_activity);
        mButtonChooseImage = findViewById(R.id.button_choose_file);
        mButtonUpload = findViewById(R.id.button_upload);
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mEditTextFileName = findViewById(R.id.edit_text_file_name);
        mImageView = findViewById(R.id.imageView);
        mProgressBar = findViewById(R.id.progress_bar);

        storage = FirebaseStorage.getInstance();
        mStorageRef =storage.getReferenceFromUrl("gs://valenzaceramic-8db2b.appspot.com/");
       // mImageRef = mStorageRef.child("Customer_Uploads");

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask != null && mUploadTask.isInProgress())
                {
                    Toast.makeText(custum_tile_activity.this , "Upload in Progress",Toast.LENGTH_SHORT).show();
                }
                uploadFile();
            }
        });
        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openImagesActivity();

            }
        });


    }
    private void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&  data != null  && data.getData()!=null)
        {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(mImageView);

            


        }
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile()
    {
        if(mImageUri != null)
        {
            StorageReference fileRefrence = mStorageRef.child( "t"+Math.random()*10000+"."+getFileExtension(mImageUri) );
          mUploadTask =   fileRefrence.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            },500);
                            Toast.makeText(custum_tile_activity.this,"Upload Successful" , Toast.LENGTH_SHORT).show();
                            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getStorage().getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            String idname = "t786";
                            mDatabaseRef.child(idname).setValue(upload);
                            //try new



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(custum_tile_activity.this,"Upload Failed" , Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);
                        }
                    });

        }
        else
        {
            Toast.makeText(this,"No File Selected!", LENGTH_SHORT).show();


        }
    }
    //changed name
    private void openImagesActivity()
    {
        Intent intent = new Intent(this,Tiles_List_Activity.class);
        startActivity(intent);
    }
}
