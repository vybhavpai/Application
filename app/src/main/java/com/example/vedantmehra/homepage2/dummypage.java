package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dummypage extends AppCompatActivity {
    private DatabaseReference mRef;
    private DatabaseReference mRef1,mref2,mref3,mref4,mref5,mref6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummypage);
        mRef = FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getUid());
        mRef1 = mRef.child("idea");
        mref2 = mRef1.child("ideaTitle");
        mref3 = mRef1.child("ideaBody");
        mref4 = mRef1.child("ideaCost");
        mref5 = mRef1.child("ideaTime");
        mref6 = mRef1.child("workType");

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");
        String Cost = intent.getStringExtra("Cost");
        String WorkType = intent.getStringExtra("WorkType");
        String Time = intent.getStringExtra("Time");
        final EditText etLocation = (EditText) findViewById(R.id.Title);
        final EditText etLocation1 = (EditText) findViewById(R.id.Body);
        final EditText etLocation2 = (EditText) findViewById(R.id.est_cost);
        final EditText etLocation3 = (EditText) findViewById(R.id.type);
        final EditText etLocation4 = (EditText) findViewById(R.id.time);
        mref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                etLocation.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                etLocation1.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                etLocation2.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                etLocation4.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                etLocation3.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void goBack(View view) {
        Intent goBack = new Intent();
        EditText etLocation = (EditText) findViewById(R.id.Title);
        EditText etLocation1 = (EditText) findViewById(R.id.Body);
        EditText etLocation2 = (EditText) findViewById(R.id.est_cost);
        EditText etLocation3 = (EditText) findViewById(R.id.type);
        EditText etLocation4 = (EditText) findViewById(R.id.time);
        String data1 = etLocation.getText().toString();
        String data2 = etLocation1.getText().toString();
        String data3 = etLocation2.getText().toString();
        String data4 = etLocation3.getText().toString();
        String data5 = etLocation4.getText().toString();
          mRef1.child("ideaTitle").setValue(data1);
          mRef1.child("ideaBody").setValue(data2);
          mRef1.child("ideaCost").setValue(data3);
          mRef1.child("ideaTime").setValue(data5);
          mRef1.child("workType").setValue(data4);
    setResult(RESULT_OK,goBack);
    finish();
     }
}
