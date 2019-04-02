package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class rating2 extends AppCompatActivity {
    TextView textView;
    String pos;
    double a=0;
    int cnt=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating2);
        Intent notification = getIntent();
        pos = notification.getStringExtra("id");
        textView = findViewById(R.id.textView19);
        DatabaseReference current_user= FirebaseDatabase.getInstance().getReference("user").child(pos).child("rating");
        current_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    a = a + Double.parseDouble(dataSnapshot1.getValue().toString());
                    cnt++;
                }
                a=a/cnt;
                textView.setText(Double.toString(a));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
