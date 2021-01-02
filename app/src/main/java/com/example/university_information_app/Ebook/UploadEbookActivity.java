package com.example.university_information_app.Ebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.university_information_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class UploadEbookActivity extends AppCompatActivity {
    private CardView addPdf;
    private EditText pdfTittle;
    private Button uploadPdfBtn;
    private TextView pdfTextView;  //show file name
    private  String pdfName ,tittle;  //store file name

    private final int REQ=1;
    private Uri pdfData;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    String downloadUrl = "";
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_ebook);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        pd =new ProgressDialog(this);

        addPdf = findViewById(R.id.addPdf);
        pdfTittle =findViewById(R.id.pdfTittle);
        uploadPdfBtn =findViewById(R.id.uploadPdfBtn);
        pdfTextView =findViewById(R.id.pdfTextView);

        addPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        uploadPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                tittle= pdfTittle.getText().toString();
                if(tittle.isEmpty()){
                    pdfTittle.setError("Empty");
                    pdfTittle.requestFocus();
                }else if (pdfData == null){
                    Toast.makeText(UploadEbookActivity.this, "Please upload pdf", Toast.LENGTH_SHORT).show();
                }else {
                    uploadPdf();
                }
            }
        });
    }

    private void uploadPdf() {
        pd.setTitle("Please wait....");
        pd.setMessage("Uploading pdf");
        pd.show();
        StorageReference reference  =  storageReference.child("pdf/"+
                pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //Get Url using taskSnapshot
                        Task<Uri> uriTask =taskSnapshot.getStorage().getDownloadUrl();
                        // stop execution until get the downloadUrl
                        while(!uriTask.isComplete());
                        Uri uri =uriTask.getResult();
                        uploadData(String.valueOf(uri));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadEbookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData(String downloadUrl) {

        String uniqueKey = databaseReference.child("pdf").push().getKey(); //store data in a uniqueKey
        HashMap data =new HashMap();// store Data using HashMap
        data.put("pdfTittle",tittle);
        data.put("pdfUrl",downloadUrl);

        databaseReference.child("pdf").child(uniqueKey).setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(UploadEbookActivity.this, "Pdf Upload Successfully", Toast.LENGTH_SHORT).show();
                        pdfTittle.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadEbookActivity.this, "Failed to Upload Pdf", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("application/pdf"); //intent.setType("pdf/docs/ppt/*/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf Tittle"),REQ);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==REQ && resultCode== RESULT_OK){
            pdfData = data.getData();
            String uriString = pdfData.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;
            //Get File Name
            if(uriString.startsWith("content://")){
                Cursor cursor = null;
                try {
                    cursor =UploadEbookActivity.this.getContentResolver().query(pdfData,null,
                            null,null,null);

                    if (cursor !=null && cursor.moveToFirst()){
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }else if(uriString.startsWith("file://")){
                displayName = myFile.getName();
            }

            //Set PDF File Name
            pdfTextView.setText(displayName);
        }
    }
}