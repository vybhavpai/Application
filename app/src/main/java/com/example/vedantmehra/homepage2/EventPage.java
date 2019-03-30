package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class EventPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        Bundle bundle = getIntent().getExtras();

//        Serializable eventObj = bundle.getSerializable("EVENT_NAME");
        TextView nameView = findViewById(R.id.name_text_view);
        nameView.setText("EVENT NAME : "+ bundle.getString("EVENT_NAME"));
        TextView descriptionView = findViewById(R.id.info_text_view);
        descriptionView.setText(bundle.getString("DESCRIPTION"));
        TextView dateView = findViewById(R.id.datetext_text_view);
        dateView.setText(bundle.getString("DATE"));
        TextView venueView = findViewById(R.id.venuetext_text_view);
        venueView.setText(bundle.getString("VENUE"));
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        Intent intent = new Intent(EventPage.this, relation.class);
                        Toast.makeText(EventPage.this, "Relations", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_edit:
                        intent = new Intent(EventPage.this, NotificationMain.class);
                        Toast.makeText(EventPage.this, "Notification", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_remove:
                        intent = new Intent(EventPage.this, profile_student.class);
                        Toast.makeText(EventPage.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
