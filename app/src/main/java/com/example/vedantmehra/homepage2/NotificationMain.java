package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationMain extends AppCompatActivity {

    public static int status,position;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mNodeReference;
    Intent intent;
    String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent notificaionresponse = getIntent();
//        position = notificaionresponse.getIntExtra("position",0);
//        status = notificaionresponse.getIntExtra("status",0);


        // Set the content of the activity to use the notification_main.xml.xml layout file
        setContentView(R.layout.activity_notification_main);

        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager(),status,position);

        pager.setAdapter(adapter);

        TabLayout tab = (TabLayout)findViewById(R.id.tab);
        tab.setupWithViewPager(pager);


        mNodeReference = FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getUid());


        mNodeReference.child("tag").child("tag").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tag = dataSnapshot.getValue().toString();

                if(tag.equals("0"))
                    intent = new Intent(NotificationMain.this, HomePageStudent.class);
                if(tag.equals("1"))
                    intent = new Intent(NotificationMain.this, HomePage.class);
                if(tag.equals("2"))
                    intent = new Intent(NotificationMain.this, HomePageMentor.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onBackPressed() {


        startActivity(intent);
        super.onBackPressed();
    }
}