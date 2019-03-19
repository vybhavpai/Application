package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class dummypage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummypage);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");
        String Cost = intent.getStringExtra("Cost");
        String WorkType = intent.getStringExtra("WorkType");
        String Time = intent.getStringExtra("Time");
        EditText textveiw = (EditText) findViewById(R.id.Title);
        textveiw.setText(title);
        EditText textveiw2 = (EditText) findViewById(R.id.Body);
        textveiw2.setText(body);
        EditText textveiw3 = (EditText) findViewById(R.id.est_cost);
        textveiw3.setText(Cost);
        EditText textveiw4 = (EditText) findViewById(R.id.type);
        textveiw4.setText(WorkType);
        EditText textveiw5 = (EditText) findViewById(R.id.time);
        textveiw5.setText(Time);
    }


    public void goBack(View view) {
        Intent goBack = new Intent();
        EditText etLocation = (EditText) findViewById(R.id.Title);
        EditText etLocation1 = (EditText) findViewById(R.id.Body);
        EditText etLocation2 = (EditText) findViewById(R.id.est_cost);
        EditText etLocation3 = (EditText) findViewById(R.id.type);
        EditText etLocation4 = (EditText) findViewById(R.id.time);
        goBack.putExtra("location", etLocation.getText().toString());
        goBack.putExtra("location1", etLocation1.getText().toString());
        goBack.putExtra("location2", etLocation2.getText().toString());
        goBack.putExtra("location3", etLocation3.getText().toString());
        goBack.putExtra("location4", etLocation4.getText().toString());
        setResult(RESULT_OK,goBack);
        finish();
    }
}
