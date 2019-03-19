package com.example.vedantmehra.homepage2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class notification extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        Intent intent = new Intent(notification.this, relation.class);
                        Toast.makeText(notification.this, "Relations", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_edit:
                        intent = new Intent(notification.this, notification.class);
                        Toast.makeText(notification.this, "Notification", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_remove:
                        intent = new Intent(notification.this, profile_student.class);
                        Toast.makeText(notification.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        Intent a = getIntent();
        int x = a.getIntExtra("return_value",0);
        if(x == 1 || x == 2)
            hidenotification(x);
    }

    private void hidenotification(int x) {
        RelativeLayout friend_request = (RelativeLayout) findViewById(R.id.friend_request);
        //friend_request.setVisibility(View.GONE);
        friend_request.setEnabled(false);
        if(x == 1) {
            TextView header = (TextView) findViewById(R.id.header2);
            header.setText("You have accepted NAME's friend request");
            TextView sub_header = (TextView) findViewById(R.id.sub_header2);
            sub_header.setVisibility(View.GONE);

            // Toast.makeText(MainActivity.this, "you have accepted the friend request", Toast.LENGTH_LONG);
        }
        else
        {
            TextView header = (TextView) findViewById(R.id.header2);
            header.setText("You have declined NAME's friend request");
            TextView sub_header = (TextView) findViewById(R.id.sub_header2);
            sub_header.setVisibility(View.GONE);

            //Toast.makeText(MainActivity.this,"you have rejected the friend request",Toast.LENGTH_LONG);
        }

    }

    public void eventpage(View view) {
        Intent a = new Intent(this,EventPage.class);
        startActivity(a);
    }

    public void friendpage(View view) {
        Intent a = new Intent(this,Request.class);
        startActivity(a);
    }
}



