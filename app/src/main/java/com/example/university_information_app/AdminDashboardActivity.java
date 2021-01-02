package com.example.university_information_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.university_information_app.Ebook.UploadEbookActivity;
import com.example.university_information_app.Gallery.UploadImage;
import com.example.university_information_app.Notice.DeleteNoticeActivty;
import com.example.university_information_app.Notice.UploadNoticeActivity;
import com.example.university_information_app.faculty.UpdateFaculty;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView uploadNotice;
    private  CardView addGalleryImage ,uploadEbook ,faculty,deleteNotice;

    FirebaseAuth auth  =FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        uploadNotice = (CardView) findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        uploadEbook = (CardView) findViewById(R.id.uploadEbook);
        faculty = (CardView) findViewById(R.id.faculty);
        deleteNotice=findViewById(R.id.deleteNotice);

        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        uploadEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (user == null){
            startActivity(new Intent(this,LogInActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
          switch (view.getId()){
              case  R.id.addNotice:
                   intent = new Intent( AdminDashboardActivity.this, UploadNoticeActivity.class);
                  startActivity(intent);
                  break;
              case  R.id.addGalleryImage:
                  intent = new Intent( AdminDashboardActivity.this, UploadImage.class);
                  startActivity(intent);
                  break;
              case  R.id.uploadEbook:
                  intent = new Intent( AdminDashboardActivity.this, UploadEbookActivity.class);
                  startActivity(intent);
                  break;
              case  R.id.faculty:
                  intent = new Intent( AdminDashboardActivity.this, UpdateFaculty.class);
                  startActivity(intent);
                  break;
              case  R.id.deleteNotice:
                  intent = new Intent( AdminDashboardActivity.this, DeleteNoticeActivty.class);
                  startActivity(intent);
                  break;

          }
    }
}