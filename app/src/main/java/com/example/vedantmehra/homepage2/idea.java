package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class idea extends AppCompatActivity {
    private static final int REQ_CODE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String title,body;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        Intent intent = new Intent(idea.this, HomePage.class);
                        Toast.makeText(idea.this, "Home", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_edit:
                        intent = new Intent(idea.this, NotificationMain.class);
                        Toast.makeText(idea.this, "Notification", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_remove:
                        intent = new Intent(idea.this, profile_student.class);
                        Toast.makeText(idea.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
    public void editPage(View view){
        Intent intent = new Intent(this , dummypage.class);
        TextView et_Location = (TextView) findViewById(R.id.title);
        TextView et_Location1 = (TextView) findViewById(R.id.body);
        TextView et_Location2 = (TextView) findViewById(R.id.Cost);
        TextView et_Location3 = (TextView) findViewById(R.id.WorkType);
        TextView et_Location4 = (TextView) findViewById(R.id.Time);
        intent.putExtra("title",et_Location.getText().toString());
        intent.putExtra("body",et_Location1.getText().toString());
        intent.putExtra("Cost",et_Location2.getText().toString());
        intent.putExtra("WorkType",et_Location3.getText().toString());
        intent.putExtra("Time",et_Location4.getText().toString());
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE){
            String heading = data.getStringExtra("location");
            String bod = data.getStringExtra("location1");
            String bod1 = data.getStringExtra("location2");
            String bod2 = data.getStringExtra("location3");
            String bod3 = data.getStringExtra("location4");
            TextView textveiw = (TextView) findViewById(R.id.title);
            textveiw.setText(heading);
            TextView textveiw2 = (TextView) findViewById(R.id.body);
            textveiw2.setText(bod);
            TextView textveiw3 = (TextView) findViewById(R.id.Cost);
            textveiw3.setText(bod1);
            TextView textveiw4 = (TextView) findViewById(R.id.WorkType);
            textveiw4.setText(bod2);
            TextView textveiw5 = (TextView) findViewById(R.id.Time);
            textveiw5.setText(bod3);
        }
    }
}
