package com.example.vedantmehra.homepage2;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestPage extends AppCompatActivity {

    DatabaseReference databaseReferenceNotification,y,x,sender,reference,currentuser, reciever;
    FirebaseDatabase firebaseDatabase;
    String name,id;
    int childcount;

    static int pos,money,type,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);
        Intent notification = getIntent();

        pos = notification.getIntExtra("position",0);
        money = notification.getIntExtra("money",0);

        final TextView nameofsender = (TextView)findViewById(R.id.name_in_request);

        x = FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getUid());
        databaseReferenceNotification = x.child("notification");

        databaseReferenceNotification.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int cnt = 0;
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    if(cnt == pos){
                        y=data.child("status").getRef();
                        name = data.child("name").getValue(String.class);
                        type = Integer.parseInt(data.child("status").getValue().toString());
                        if(type == 0)
                        {
                            nameofsender.setText(name + " has sent you a friend request");
                        }
                        else
                            if(type == 4)
                            {
                                amount = Integer.parseInt(data.child("amount").getValue().toString());
                                nameofsender.setText(name + " has sent you amount Rs." + amount );
                            }
                        id = data.child("id").getValue(String.class);

                        break;
                    }
                    cnt++;
                    Log.d("check","name :" + name);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void notificationpageaccept(View view) {
        Intent intent = new Intent(this,NotificationMain.class);
        if(type == 0) {
            y.setValue("1");

            firebaseDatabase = FirebaseDatabase.getInstance();
            sender = firebaseDatabase.getReference("user/" + id);
            reference = sender.child("relation");
            reference.child(FirebaseAuth.getInstance().getUid()).setValue(FirebaseAuth.getInstance().getUid());
            reference = x.child("relation");
            reference.child(id).setValue(id);
        }
        else
        if(type == 4) {
            y.setValue("5");
        }
        startActivity(intent);
    }

    public void notificationpagereject(View view) {
        Intent intent = new Intent(this,NotificationMain.class);
        if(type == 0) {
            y.setValue("2");
        }
        else
            if(type == 4)
            {
                y.setValue("6");
                firebaseDatabase = FirebaseDatabase.getInstance();
                sender = firebaseDatabase.getReference("user/" + id);

                sender.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer currentBal = Integer.parseInt(dataSnapshot.child("money").getValue().toString());
                        currentBal += amount;
                        String temp = String.valueOf(currentBal);
                        sender.child("money").setValue(temp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                reciever = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                reciever.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer currentBal = Integer.parseInt(dataSnapshot.child("money").getValue().toString());
                        currentBal -= amount;
                        String temp = String.valueOf(currentBal);
                        reciever.child("money").setValue(temp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        startActivity(intent);
    }

    public void visitprofile(View view) {

        Intent a = new Intent(this,profile_student_1.class);
        a.putExtra("id",id);
        startActivity(a);

    }
}

