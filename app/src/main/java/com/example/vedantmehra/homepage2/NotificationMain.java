package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationMain extends AppCompatActivity {

    public static int status,position;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mNodeReference;
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



    }
}