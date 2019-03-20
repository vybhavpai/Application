package com.example.vedantmehra.homepage2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewDummyActivity extends AppCompatActivity {

    DatabaseReference databaseReferenceStudent, databaseReference;
    String value = "";
    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dummy);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            value = bundle.getString("studentId");
        }
        //databaseReferenceStudent = FirebaseDatabase.getInstance().getReference("student").child(value);
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(value);
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t1.setText(dataSnapshot.child("profile").child("name").getValue().toString());
                t2.setText(dataSnapshot.child("profile").child("userEmail").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
