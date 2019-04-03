package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ideasearch extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user"),mref1,
    mref2,mref3,mref4,mref5,mref6,mref10,mref9,mref7,mref8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideasearch);

        DatabaseReference mRef;
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mRef = FirebaseDatabase.getInstance().getReference("user");
        // String temp = mRef.child(currentUserId).child("tag").child("tag").toString();
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        Intent intent;
                        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String temp = dataSnapshot.child(currentUserId).child("tag").child("tag").getValue().toString();
                                Intent intent;
                                if(temp.equals("0"))
                                    intent = new Intent(ideasearch.this, HomePageStudent.class);
                                else if(temp.equals("1"))
                                    intent = new Intent(ideasearch.this, HomePage.class);
                                else
                                    intent = new Intent(ideasearch.this, HomePageMentor.class);
                                Toast.makeText(ideasearch.this, "Home", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        break;
                    case R.id.action_edit:
                        intent = new Intent(ideasearch.this, NotificationMain.class);
                        Toast.makeText(ideasearch.this, "Notification", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_remove:
                        intent = new Intent(ideasearch.this, profile_student.class);
                        Toast.makeText(ideasearch.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        Intent intent = getIntent();

        String pos = intent.getStringExtra("id");
        ref = ref.child(pos);
        mref1 = ref.child("idea");
        mref2 = mref1.child("ideaTitle");
        mref3 = mref1.child("ideaBody");
        mref4 = mref1.child("ideaCost");
        mref5 = mref1.child("ideaTime");
        mref6 = mref1.child("workType");
        mref7 = ref.child("profile");
        mref8 = mref7.child("name");
        mref9 = mref7.child("branch");
        mref10 = mref7.child("school");

        final TextView et_Location = (TextView) findViewById(R.id.title);
        final TextView et_Location1 = (TextView) findViewById(R.id.body);
        final TextView et_Location2 = (TextView) findViewById(R.id.Cost);
        final TextView et_Location3 = (TextView) findViewById(R.id.WorkType);
        final TextView et_Location4 = (TextView) findViewById(R.id.Time);
        final TextView et_Location5 = (TextView) findViewById(R.id.Discipline);
        final TextView et_Location6 = (TextView) findViewById(R.id.textView);
        final TextView et_Location7 = (TextView) findViewById(R.id.ClgName);


        mref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                et_Location.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                et_Location6.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                et_Location5.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                et_Location7.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                et_Location1.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                et_Location2.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                et_Location4.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                et_Location3.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
