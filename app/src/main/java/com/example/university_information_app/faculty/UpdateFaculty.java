package com.example.university_information_app.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.university_information_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {
    FloatingActionButton fab;
    private RecyclerView csDepartment,eeeDepartment,pharmacyDepartment ,ellDepartment;
    private LinearLayout csNoData,eeeNoData,pharmacyNoData,ellNoData;
    private List<TeacherData> list1,list2,list3,list4;
    private TeacherAdapter adapter;

    private DatabaseReference databaseReference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        csDepartment =findViewById(R.id.csDepartment);
        eeeDepartment= findViewById(R.id.eeeDepartment);
        pharmacyDepartment = findViewById(R.id.pharmacyDepartment);
        ellDepartment=findViewById(R.id.ellDepartment);

        csNoData =findViewById(R.id.csNoData);
        pharmacyNoData= findViewById(R.id.pharmacyNoData);
        eeeNoData = findViewById(R.id.eeeNoData);
        ellNoData=findViewById(R.id.ellNoData);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("teacher");
        
        csDepartment();
        eeeDepartment();
        pharmacyDepartment();
        ellDepartment();


        fab= findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateFaculty.this,AddFacultyActivity.class));
            }
        });
    }

    private void csDepartment() {
        dbRef=databaseReference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            list1 = new ArrayList<>();
            if(!dataSnapshot.exists()){
                csNoData.setVisibility(View.VISIBLE);
                csDepartment.setVisibility(View.GONE);

              }else {
                csNoData.setVisibility(View.GONE);
                csDepartment.setVisibility(View.VISIBLE);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    TeacherData data = snapshot.getValue(TeacherData.class);
                    list1.add(data);
                }
                csDepartment.setHasFixedSize(true);
                csDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                adapter = new TeacherAdapter(list1,UpdateFaculty.this,"Computer Science");
                csDepartment.setAdapter(adapter);
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeeDepartment() {
        dbRef=databaseReference.child("EEE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    eeeNoData.setVisibility(View.VISIBLE);
                    eeeDepartment.setVisibility(View.GONE);

                }else {
                    eeeNoData.setVisibility(View.GONE);
                    eeeDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:
                            dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    eeeDepartment.setHasFixedSize(true);
                    eeeDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2,UpdateFaculty.this,"EEE");
                    eeeDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pharmacyDepartment() {
        dbRef=databaseReference.child("Pharmacy");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    pharmacyNoData.setVisibility(View.VISIBLE);
                    pharmacyDepartment.setVisibility(View.GONE);

                }else {
                    pharmacyNoData.setVisibility(View.GONE);
                    pharmacyDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:
                            dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    pharmacyDepartment.setHasFixedSize(true);
                    pharmacyDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3,UpdateFaculty.this,"Pharmacy");
                    pharmacyDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ellDepartment() {
        dbRef=databaseReference.child("Ell");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    ellNoData.setVisibility(View.VISIBLE);
                    ellDepartment.setVisibility(View.GONE);

                }else {
                    ellNoData.setVisibility(View.GONE);
                    ellDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:
                            dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    ellDepartment.setHasFixedSize(true);
                    ellDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4,UpdateFaculty.this,"Ell");
                    ellDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}