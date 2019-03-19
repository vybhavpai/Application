package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
    }

    public void notificationpageaccept(View view) {
        Intent intent = new Intent(this,notification.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("return_value",1);
        startActivity(intent);
    }

    public void notificationpagereject(View view) {
        Intent intent = new Intent(this,notification.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("return_value",2);
        startActivity(intent);
    }

    public void visitprofile(View view) {

        Intent a = new Intent(this,profile_student_1.class);
        startActivity(a);

    }
}