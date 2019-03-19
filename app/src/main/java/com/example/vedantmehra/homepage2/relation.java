package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class relation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation);
    }

    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent(relation.this, mentor_profile.class);
        Toast.makeText(relation.this, "Mentor Profile", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
