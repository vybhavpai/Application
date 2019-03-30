package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletPageInvestor extends AppCompatActivity {

    private Button addMoney, sendMoney, currentBalanceButton, GoToRelationPage;
    private EditText amount, userN;
    private DatabaseReference databaseReference;
    private TextView currentBalanceText;
    String id, userName, studentId;

    // value entered by the user
    Integer val;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_page_investor);

        if(FirebaseAuth.getInstance() != null && FirebaseAuth.getInstance().getCurrentUser() != null)
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        else
            return;

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        addMoney = (Button)findViewById(R.id.add_money_btn);
        sendMoney = (Button)findViewById(R.id.send_money_btn);
        amount = (EditText)findViewById(R.id.amt);
        userN = (EditText)findViewById(R.id.userName);
        GoToRelationPage = findViewById(R.id.relationsBtn);
        currentBalanceButton = (Button)findViewById(R.id.currentBalBtn);
        currentBalanceText = (TextView)findViewById(R.id.currentBalText);

        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String type = dataSnapshot.child("tag").child("tag").getValue().toString();
                if(type.equals("0")){
                    Intent intent = new Intent(WalletPageInvestor.this, WalletPageStudent.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        currentBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("money").exists()){
                            String s = dataSnapshot.child("money").getValue().toString();
                            currentBalanceText.setText("Balance " + s);
                        }else{
                            currentBalanceText.setText("Balance 0");
                        }
                        for (int i = 0 ; i < 1000000 ; i++){
                            ;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletPageInvestor.this, AddMoneyPage.class);
                startActivity(intent);
            }
        });

        GoToRelationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletPageInvestor.this, relation.class);
                intent.putExtra("1", "1");
                startActivity(intent);
            }
        });

        /*addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountToAdd = (amount.getText().toString());
                String emailEntered = userN.getText().toString();
                if(!TextUtils.isEmpty(emailEntered)){
                    Toast.makeText(WalletPageInvestor.this, "Only enter the money field", Toast.LENGTH_LONG).show();
                    return;
                }

                userN.setText("");
                amount.setText("");
                if(!TextUtils.isEmpty(amountToAdd)){

                    val = Integer.parseInt(amountToAdd);
                    databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("money").exists())
                            {
                                Integer value = Integer.parseInt(dataSnapshot.child("money").getValue().toString());
                                val += value;
                                databaseReference.child(id).child("money").setValue(val);
                            }else {
                                databaseReference.child(id).child("money").setValue(val);
                            }
                            Toast.makeText(WalletPageInvestor.this, "Money added", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Toast.makeText(WalletPageInvestor.this, "Enter Money field", Toast.LENGTH_SHORT).show();
                }
            }
        });*/



        sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userN.getText().toString();
                userN.setText("");
                final String amountToSend = amount.getText().toString();
                amount.setText("");
                flag = false;

                if(!TextUtils.isEmpty(userName) || !TextUtils.isEmpty(amountToSend)){
                    val = Integer.parseInt(amountToSend);
                    databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.child("money").exists()){
                                Toast.makeText(WalletPageInvestor.this, "Insufficient Balance", Toast.LENGTH_LONG).show();
                                return;
                            }
                            final String nameToSend = dataSnapshot.child("profile").child("name").getValue().toString();
                            final Integer value = Integer.parseInt(dataSnapshot.child("money").getValue().toString());
                            if(val > value){
                                Toast.makeText(WalletPageInvestor.this, "Insufficient Balance", Toast.LENGTH_LONG).show();
                                return;
                            }else{

                                if(!dataSnapshot.child("relation").exists()){
                                    Toast.makeText(WalletPageInvestor.this, "No user exists", Toast.LENGTH_SHORT).show();
                                }

                                for(DataSnapshot dataSnapshot1 : dataSnapshot.child("relation").getChildren()){
                                    studentId = dataSnapshot1.getValue().toString();
                                    Log.d("WalletPage", "here1 " + studentId);
                                    final String[] studentEmail = {""};
                                    final int[] cnt = {(int) dataSnapshot.child("relation").getChildrenCount()};
                                    databaseReference.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            studentEmail[0] = dataSnapshot.child("profile").child("userEmail").getValue().toString();
                                            if(studentEmail[0].equals(userName)){
                                                flag = true;
                                                updateStudentBalance(studentId, amountToSend);
                                                Integer amountSent = Integer.parseInt(amountToSend);
                                                Integer newBalance = value - amountSent;
                                                String temp = String.valueOf(newBalance);
                                                databaseReference.child(id).child("money").setValue(temp);
                                                Toast.makeText(WalletPageInvestor.this, "Transaction Successful", Toast.LENGTH_SHORT).show();

                                                databaseReference.child(studentId).child("notification").child(id).child("status").setValue(4);
                                                databaseReference.child(studentId).child("notification").child(id).child("id").setValue(id);
                                                databaseReference.child(studentId).child("notification").child(id).child("amount").setValue(amountSent);
                                                databaseReference.child(studentId).child("notification").child(id).child("name").setValue(nameToSend);
                                                return;
                                            }
                                            cnt[0]--;
                                            if(cnt[0] == 0){
                                                Toast.makeText(WalletPageInvestor.this, "No such user found", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                }

                                Log.d("WalletPage", "flag after all iterations " + flag);
                                /*if(flag){
                                    Log.d("WalletPage", "value of flag " + flag);
                                    Toast.makeText(WalletPage.this, "No user found", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(WalletPage.this, "Transaction successful", Toast.LENGTH_LONG).show();
                                    Log.d("WalletPage", "value of flag " + flag);
                                    int currentInvestorBalance = Integer.parseInt(dataSnapshot.child("money").getValue().toString());
                                    databaseInvestor.child(id).child("money").setValue(currentInvestorBalance - val);
                                }*/
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Toast.makeText(WalletPageInvestor.this, "Enter amount and email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    void updateStudentBalance(final String Id, final String amountToSend){
        databaseReference.child(Id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("money").exists()){
                    Integer currentBal = Integer.parseInt(dataSnapshot.child("money").getValue().toString());
                    Integer amountAdd = Integer.parseInt(amountToSend);
                    Integer temp = currentBal + amountAdd;
                    String mon = String.valueOf(temp);
                    databaseReference.child(Id).child("money").setValue(mon);
                }else{
                    databaseReference.child(Id).child("money").setValue(amountToSend);
                }
                Toast.makeText(WalletPageInvestor.this, "Transaction Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(WalletPageInvestor.this, HomePage.class);
        startActivity(intent);
    }

}
