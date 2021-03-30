package com.pebblescrow.uploadimagetodatabae;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText imageDetailsET;
    private ImageView objectImageView;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    DatabaseHandler objectDatabaseHandler;
    private static final int PICK_Image_Request= 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            imageDetailsET = findViewById(R.id.imageNameET);
            objectImageView = findViewById(R.id.image);
            objectDatabaseHandler=new DatabaseHandler(this);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void chooseImage(View objectView){
        try {
            Intent objectIntent = new Intent();
            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, PICK_Image_Request);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==PICK_Image_Request && resultCode==RESULT_OK && data!=null && data.getData()!=null)
            {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                objectImageView.setImageBitmap(imageToStore);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void storeImage(View view)
    {
        try {
            if (!imageDetailsET.getText().toString().isEmpty() && objectImageView.getDrawable()!= null && imageToStore!=null)
            {
                objectDatabaseHandler.storeImage(new ModelClass(imageDetailsET.getText().toString(),imageToStore));
            }
            else
            {
                Toast.makeText(this, "Please select name and image", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void moveToShowActivity(View view)
    {
        startActivity(new Intent(this,ShowImagesActivitiy.class));
    }
}

