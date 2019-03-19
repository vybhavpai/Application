package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profile_student_1 extends AppCompatActivity {
    Button bt;
    Button btw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student_1);
       // bt = findViewById(R.id.button);
        //btw = findViewById(R.id.button2);
       /* btw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(getApplicationContext(),idea.class);
                startActivity(inte);
            }
        });*/
    }
   public void goEdit(View view)
   {
        startActivity(new Intent(getApplicationContext(),profile_student_edit.class));
   }
}
