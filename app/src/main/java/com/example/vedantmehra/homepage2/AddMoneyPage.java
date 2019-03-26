package com.example.vedantmehra.homepage2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMoneyPage extends AppCompatActivity {

    Button addMon;
    EditText amountToAdd;
    String id;
    DatabaseReference databaseReference;
    int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money_page);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        addMon = findViewById(R.id.addMoney);
        amountToAdd = findViewById(R.id.amt);
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        addMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amountToBeAdded = amountToAdd.getText().toString();
                if(TextUtils.isEmpty(amountToBeAdded)){
                    Toast.makeText(AddMoneyPage.this, "Enter Money", Toast.LENGTH_SHORT).show();
                    return;
                }

                val = Integer.parseInt(amountToBeAdded);

                databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("money").exists()){
                            Integer value = Integer.parseInt(dataSnapshot.child("money").getValue().toString());
                            val += value;
                            databaseReference.child(id).child("money").setValue(val);
                        }else{
                            databaseReference.child(id).child("money").setValue(val);
                        }
                        amountToAdd.setText("");
                        Toast.makeText(AddMoneyPage.this, "Money added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
