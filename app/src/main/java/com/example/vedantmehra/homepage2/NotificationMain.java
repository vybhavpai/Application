package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

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

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        Intent intent = new Intent(NotificationMain.this, relation.class);
                        Toast.makeText(NotificationMain.this, "Relations", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_edit:
                        intent = new Intent(NotificationMain.this, HomePage.class);
                        Toast.makeText(NotificationMain.this, "Notification", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_remove:
                        intent = new Intent(NotificationMain.this, profile_student.class);
                        Toast.makeText(NotificationMain.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager(),status,position);

        pager.setAdapter(adapter);

        TabLayout tab = (TabLayout)findViewById(R.id.tab);
        tab.setupWithViewPager(pager);



    }
}