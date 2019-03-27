package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_student_2 extends AppCompatActivity {
    Button bt;
    Button btw;
    Button btww;
    UserProfile profile;
    String tag;
    TextView textView3,textView4,textView14,textView5,textView6,textView7,textView8,textView11,textView12,textView13;
    FirebaseAuth Mauth = FirebaseAuth.getInstance();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user"),send = FirebaseDatabase.getInstance().getReference("user");
    String uid;
    DatabaseReference user= FirebaseDatabase.getInstance().getReference("user");
    String pos;
    String name;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student_2);

        textView3 = findViewById(R.id.textView3);
        textView14 = findViewById(R.id.textView14);
        textView13=findViewById(R.id.textView13);
        textView4 = findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);
        textView7=findViewById(R.id.textView7);
        textView8=findViewById(R.id.textView8);
        textView11=findViewById(R.id.textView11);
        textView12=findViewById(R.id.textView12);
        bt = findViewById(R.id.button);
        uid = Mauth.getUid();
        Intent notification = getIntent();
        pos = notification.getStringExtra("id");
        btw = findViewById(R.id.button2);
        ref = ref.child(uid).child("relation");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int cnt = (int) dataSnapshot.getChildrenCount();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if(dataSnapshot1.getKey().equals(pos)){
                        ref = user.child(pos);
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                tag = dataSnapshot.child("tag").child("tag").getValue(String.class);
                                profile = dataSnapshot.child("profile").getValue(UserProfile.class);

                                if (tag.equals("0")) {
                                    textView3.setText(profile.name);
                                    textView13.setText("Email");
                                    textView14.setText(profile.userEmail);
                                    textView5.setText("College");
                                    textView6.setText(profile.school);
                                    textView7.setText("Degree");
                                    textView8.setText(profile.degree);
                                    textView11.setText("Year");
                                    textView12.setText(profile.graduation);
                                    Toast.makeText(profile_student_2.this, "student", Toast.LENGTH_SHORT).show();
                                    btw.setVisibility(View.INVISIBLE);
                                    bt.setVisibility(View.INVISIBLE);
                                    btww.setVisibility(View.INVISIBLE);


                                } else if (tag.equals("1")) {
                                    textView3.setText(profile.name);
                                    textView13.setText("Email");
                                    textView14.setText(profile.userEmail);
                                    textView5.setText("Company");
                                    textView6.setText(profile.company);
                                    textView7.setText("Designation");
                                    textView8.setText(profile.occupation);
                                    textView11.setVisibility(View.INVISIBLE);
                                    textView12.setVisibility(View.INVISIBLE);
                                    Toast.makeText(profile_student_2.this, "investor", Toast.LENGTH_SHORT).show();
                                    btw.setVisibility(View.INVISIBLE);
                                    bt.setVisibility(View.INVISIBLE);
                                    btww.setVisibility(View.INVISIBLE);


                                } else if (tag.equals("2")) {
                                    textView3.setText(profile.name);
                                    textView13.setText("Email");
                                    textView14.setText(profile.userEmail);
                                    textView5.setText("Company");
                                    textView6.setText(profile.company);
                                    textView7.setText("Designation");
                                    textView8.setText(profile.occupation);
                                    textView11.setVisibility(View.INVISIBLE);
                                    textView12.setVisibility(View.INVISIBLE);
                                    Toast.makeText(profile_student_2.this, "mentor", Toast.LENGTH_SHORT).show();
                                    btw.setVisibility(View.INVISIBLE);
                                    bt.setVisibility(View.INVISIBLE);
                                    btww.setVisibility(View.INVISIBLE);



                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                       /* tag = dataSnapshot.child("tag").child("tag").getValue(String.class);
                        profile = dataSnapshot.child("profile").getValue(UserProfile.class);

                        if (tag.equals("0")) {
                            textView3.setText(profile.name);
                            textView13.setText("Email");
                            textView14.setText(profile.userEmail);
                            textView5.setText("College");
                            textView6.setText(profile.school);
                            textView7.setText("Degree");
                            textView8.setText(profile.degree);
                            textView11.setText("Year");
                            textView12.setText(profile.graduation);
                            Toast.makeText(profile_student_2.this, "student", Toast.LENGTH_SHORT).show();
                            btw.setVisibility(View.INVISIBLE);
                            bt.setVisibility(View.INVISIBLE);
                            btww.setVisibility(View.INVISIBLE);


                        } else if (tag.equals("1")) {
                            textView3.setText(profile.name);
                            textView13.setText("Email");
                            textView14.setText(profile.userEmail);
                            textView5.setText("Company");
                            textView6.setText(profile.company);
                            textView7.setText("Designation");
                            textView8.setText(profile.occupation);
                            textView11.setVisibility(View.INVISIBLE);
                            textView12.setVisibility(View.INVISIBLE);
                            Toast.makeText(profile_student_2.this, "investor", Toast.LENGTH_SHORT).show();
                            btw.setVisibility(View.INVISIBLE);
                            bt.setVisibility(View.INVISIBLE);
                            btww.setVisibility(View.INVISIBLE);


                        } else if (tag.equals("2")) {
                            textView3.setText(profile.name);
                            textView13.setText("Email");
                            textView14.setText(profile.userEmail);
                            textView5.setText("Company");
                            textView6.setText(profile.company);
                            textView7.setText("Designation");
                            textView8.setText(profile.occupation);
                            textView11.setVisibility(View.INVISIBLE);
                            textView12.setVisibility(View.INVISIBLE);
                            Toast.makeText(profile_student_2.this, "mentor", Toast.LENGTH_SHORT).show();
                            btw.setVisibility(View.INVISIBLE);
                            bt.setVisibility(View.INVISIBLE);
                            btww.setVisibility(View.INVISIBLE);



                        }*/
                        break;

                    }
                    cnt--;
                    /*if(cnt == 0){
                        ref = user.child(pos);
                        tag = dataSnapshot.child("tag").child("tag").getValue(String.class);
                        profile = dataSnapshot.child("profile").getValue(UserProfile.class);

                        if (tag.equals("0")) {
                            textView3.setText(profile.name);
                            textView13.setText("Email");
                            textView14.setText(profile.userEmail);
                            textView5.setText("College");
                            textView6.setText(profile.school);
                            textView7.setText("Degree");
                            textView8.setText(profile.degree);
                            textView11.setText("Year");
                            textView12.setText(profile.graduation);
                            btw.setVisibility(View.INVISIBLE);
                            btww.setVisibility(View.INVISIBLE);
                            Toast.makeText(profile_student_2.this, "student", Toast.LENGTH_SHORT).show();


                        } else if (tag.equals("1")) {
                            textView3.setText(profile.name);
                            textView13.setText("Email");
                            textView14.setText(profile.userEmail);
                            textView5.setText("Company");
                            textView6.setText(profile.company);
                            textView7.setText("Designation");
                            textView8.setText(profile.occupation);
                            textView11.setVisibility(View.INVISIBLE);
                            textView12.setVisibility(View.INVISIBLE);
                            Toast.makeText(profile_student_2.this, "investor", Toast.LENGTH_SHORT).show();
                            btw.setVisibility(View.INVISIBLE);
                            btww.setVisibility(View.INVISIBLE);


                        } else if (tag.equals("2")) {
                            textView3.setText(profile.name);
                            textView13.setText("Email");
                            textView14.setText(profile.userEmail);
                            textView5.setText("Company");
                            textView6.setText(profile.company);
                            textView7.setText("Designation");
                            textView8.setText(profile.occupation);
                            textView11.setVisibility(View.INVISIBLE);
                            textView12.setVisibility(View.INVISIBLE);
                            Toast.makeText(profile_student_2.this, "mentor", Toast.LENGTH_SHORT).show();
                            btw.setVisibility(View.INVISIBLE);
                            btww.setVisibility(View.INVISIBLE);



                        }



                    }*/
                }
                if(cnt == 0){
                    ref = user.child(pos);


                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            tag = dataSnapshot.child("tag").child("tag").getValue(String.class);
                            profile = dataSnapshot.child("profile").getValue(UserProfile.class);
                            if (tag.equals("0")) {
                                textView3.setText(profile.name);
                                textView13.setText("Email");
                                textView14.setText(profile.userEmail);
                                textView5.setText("College");
                                textView6.setText(profile.school);
                                textView7.setText("Degree");
                                textView8.setText(profile.degree);
                                textView11.setText("Year");
                                textView12.setText(profile.graduation);
                                btw.setVisibility(View.INVISIBLE);
                                btww.setVisibility(View.INVISIBLE);
                                Toast.makeText(profile_student_2.this, "student", Toast.LENGTH_SHORT).show();


                            } else if (tag.equals("1")) {
                                textView3.setText(profile.name);
                                textView13.setText("Email");
                                textView14.setText(profile.userEmail);
                                textView5.setText("Company");
                                textView6.setText(profile.company);
                                textView7.setText("Designation");
                                textView8.setText(profile.occupation);
                                textView11.setVisibility(View.INVISIBLE);
                                textView12.setVisibility(View.INVISIBLE);
                                Toast.makeText(profile_student_2.this, "investor", Toast.LENGTH_SHORT).show();
                                btw.setVisibility(View.INVISIBLE);
                                btww.setVisibility(View.INVISIBLE);


                            } else if (tag.equals("2")) {
                                textView3.setText(profile.name);
                                textView13.setText("Email");
                                textView14.setText(profile.userEmail);
                                textView5.setText("Company");
                                textView6.setText(profile.company);
                                textView7.setText("Designation");
                                textView8.setText(profile.occupation);
                                textView11.setVisibility(View.INVISIBLE);
                                textView12.setVisibility(View.INVISIBLE);
                                Toast.makeText(profile_student_2.this, "mentor", Toast.LENGTH_SHORT).show();
                                btw.setVisibility(View.INVISIBLE);
                                btww.setVisibility(View.INVISIBLE);



                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    /*if (tag.equals("0")) {
                        textView3.setText(profile.name);
                        textView13.setText("Email");
                        textView14.setText(profile.userEmail);
                        textView5.setText("College");
                        textView6.setText(profile.school);
                        textView7.setText("Degree");
                        textView8.setText(profile.degree);
                        textView11.setText("Year");
                        textView12.setText(profile.graduation);
                        btw.setVisibility(View.INVISIBLE);
                        btww.setVisibility(View.INVISIBLE);
                        Toast.makeText(profile_student_2.this, "student", Toast.LENGTH_SHORT).show();


                    } else if (tag.equals("1")) {
                        textView3.setText(profile.name);
                        textView13.setText("Email");
                        textView14.setText(profile.userEmail);
                        textView5.setText("Company");
                        textView6.setText(profile.company);
                        textView7.setText("Designation");
                        textView8.setText(profile.occupation);
                        textView11.setVisibility(View.INVISIBLE);
                        textView12.setVisibility(View.INVISIBLE);
                        Toast.makeText(profile_student_2.this, "investor", Toast.LENGTH_SHORT).show();
                        btw.setVisibility(View.INVISIBLE);
                        btww.setVisibility(View.INVISIBLE);


                    } else if (tag.equals("2")) {
                        textView3.setText(profile.name);
                        textView13.setText("Email");
                        textView14.setText(profile.userEmail);
                        textView5.setText("Company");
                        textView6.setText(profile.company);
                        textView7.setText("Designation");
                        textView8.setText(profile.occupation);
                        textView11.setVisibility(View.INVISIBLE);
                        textView12.setVisibility(View.INVISIBLE);
                        Toast.makeText(profile_student_2.this, "mentor", Toast.LENGTH_SHORT).show();
                        btw.setVisibility(View.INVISIBLE);
                        btww.setVisibility(View.INVISIBLE);



                    }*/



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

      /*  ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tag = dataSnapshot.child("tag").child("tag").getValue(String.class);
                profile = dataSnapshot.child("profile").getValue(UserProfile.class);

                if (tag.equals("0")) {
                    textView3.setText(profile.name);
                    textView13.setText("Email");
                    textView14.setText(profile.userEmail);
                    textView5.setText("College");
                    textView6.setText(profile.school);
                    textView7.setText("Degree");
                    textView8.setText(profile.degree);
                    textView11.setText("Year");
                    textView12.setText(profile.graduation);

                } else if (tag.equals("1")) {
                    textView3.setText(profile.name);
                    textView13.setText("Email");
                    textView14.setText(profile.userEmail);
                    textView5.setText("Company");
                    textView6.setText(profile.company);
                    textView7.setText("Designation");
                    textView8.setText(profile.occupation);
                    textView11.setVisibility(View.INVISIBLE);
                    textView12.setVisibility(View.INVISIBLE);
                    btw.setVisibility(View.INVISIBLE);


                } else if (tag.equals("2")) {
                    textView3.setText(profile.name);
                    textView13.setText("Email");
                    textView14.setText(profile.userEmail);
                    textView5.setText("Company");
                    textView6.setText(profile.company);
                    textView7.setText("Designation");
                    textView8.setText(profile.occupation);
                    textView11.setVisibility(View.INVISIBLE);
                    textView12.setVisibility(View.INVISIBLE);
                    btw.setVisibility(View.INVISIBLE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomePage.class);
                send = FirebaseDatabase.getInstance().getReference("user");
                send.child(pos).child("notification").child(uid).child("id").setValue(uid);
                send.child(pos).child("notification").child(uid).child("status").setValue(0);

                send = FirebaseDatabase.getInstance().getReference("user");
                send  = send.child(uid).child("profile");
                Log.d("send","key : " + send.getKey());

                send.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        name = dataSnapshot.child("name").getValue().toString();
                        Log.d("send", "name : " + name);
                        user = FirebaseDatabase.getInstance().getReference("user");
                        user.child(pos).child("notification").child(uid).child("name").setValue(name);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//                send.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                        if (dataSnapshot.getKey().equals("name")) ;
//                        {
//                            name = dataSnapshot.getValue().toString();
//
//                            Log.d("checksend", "name is " + name);
//                        }
//                    }
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

//                to whom you are sdending
//                        uid = current
                startActivity(intent);
            }
        });
        btw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getApplicationContext(), idea.class);
                startActivity(inte);
            }
        });
        btww = findViewById(R.id.button3);
        btww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(getApplicationContext(), changepassword.class);
                startActivity(intt);
            }
        });
    }
}

