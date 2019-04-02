package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_notification extends AppCompatActivity {
    Button bt;
    Button btw;
    Button btww;
    UserProfile profile;
    String tag;
    TextView textView3,textView4,textView14,textView5,textView6,textView7,textView8,textView11,textView12,textView13;
    FirebaseAuth Mauth = FirebaseAuth.getInstance();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user"),send = FirebaseDatabase.getInstance().getReference("user");
    String uid;
    DatabaseReference user= FirebaseDatabase.getInstance().getReference("user");
    String pos;
    String name;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_notification);
        textView3 = findViewById(R.id.textView3);
        textView14 = findViewById(R.id.textView14);
        textView13=findViewById(R.id.textView13);
        textView4 = findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);
        textView7=findViewById(R.id.textView7);
        textView8=findViewById(R.id.textView8);
        textView11=findViewById(R.id.textView11);
        textView12=findViewById(R.id.textView12);
        bt = findViewById(R.id.button);
        uid = Mauth.getUid();
        Intent notification = getIntent();
        pos = notification.getStringExtra("id");
        btw = findViewById(R.id.button2);
        btww = findViewById(R.id.button3);
        ref = user.child(pos);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tag = dataSnapshot.child("tag").child("tag").getValue(String.class);
                profile = dataSnapshot.child("profile").getValue(UserProfile.class);
                if (tag.equals("0")) {
                    textView3.setText(profile.name);
                    textView13.setText("Email");
                    textView14.setText(profile.userEmail);
                    textView5.setText("College");
                    textView6.setText(profile.school);
                    textView7.setText("Degree");
                    textView8.setText(profile.degree);
                    textView11.setText("Year");
                    textView12.setText(profile.graduation);
                    bt.setVisibility(View.INVISIBLE);
                    btw.setVisibility(View.INVISIBLE);
                    btww.setVisibility(View.INVISIBLE);
                    Toast.makeText(profile_notification.this, "student", Toast.LENGTH_SHORT).show();


                } else if (tag.equals("1")) {
                    textView3.setText(profile.name);
                    textView13.setText("Email");
                    textView14.setText(profile.userEmail);
                    textView5.setText("Company");
                    textView6.setText(profile.company);
                    textView7.setText("Designation");
                    textView8.setText(profile.occupation);
                    textView11.setVisibility(View.INVISIBLE);
                    textView12.setVisibility(View.INVISIBLE);
                    Toast.makeText(profile_notification.this, "investor", Toast.LENGTH_SHORT).show();
                    btw.setVisibility(View.INVISIBLE);
                    btww.setVisibility(View.INVISIBLE);
                    bt.setVisibility(View.INVISIBLE);


                } else if (tag.equals("2")) {
                    textView3.setText(profile.name);
                    textView13.setText("Email");
                    textView14.setText(profile.userEmail);
                    textView5.setText("Company");
                    textView6.setText(profile.company);
                    textView7.setText("Designation");
                    textView8.setText(profile.occupation);
                    textView11.setVisibility(View.INVISIBLE);
                    textView12.setVisibility(View.INVISIBLE);
                    Toast.makeText(profile_notification.this, "mentor", Toast.LENGTH_SHORT).show();
                    btw.setVisibility(View.INVISIBLE);
                    btww.setVisibility(View.INVISIBLE);
                    bt.setVisibility(View.INVISIBLE);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
