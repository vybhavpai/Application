package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class relation extends AppCompatActivity  {

    String name, email, student_id, userType, currentUserType;
    DatabaseReference databaseReference;
    ArrayList<String> StudentId = new ArrayList<>();
    ArrayList<String[]> StudentDetail = new ArrayList<>();
    MyRecyclerAdapter adapter;
    RecyclerView recyclerView;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation);

        //Arqum
        DatabaseReference mRef;
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mRef = FirebaseDatabase.getInstance().getReference("user");
       // String temp;//= mRef.child(currentUserId).child("tag").child("tag").toString();




        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        Intent intent;
                        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String temp = dataSnapshot.child(currentUserId).child("tag").child("tag").getValue().toString();
                                Intent intent;
                                if(temp.equals("0"))
                                    intent = new Intent(relation.this, HomePageStudent.class);
                                else if(temp.equals("1"))
                                    intent = new Intent(relation.this, HomePage.class);
                                else
                                    intent = new Intent(relation.this, HomePageMentor.class);
                                Toast.makeText(relation.this, "Home", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        break;
                    case R.id.action_edit:
                        intent = new Intent(relation.this, NotificationMain.class);
                        Toast.makeText(relation.this, "Notification", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_remove:
                        intent = new Intent(relation.this, profile_student.class);
                        Toast.makeText(relation.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        //Arqum

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b != null){
            flag = true;
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        recyclerView = (RecyclerView)findViewById(R.id.rvStudent);
        RecyclerView.LayoutManager  manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MyRecyclerAdapter(this, StudentDetail);
        recyclerView.setAdapter(adapter);


        //String id1 = "userName";
        String id1 = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child(id1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentUserType = dataSnapshot.child("tag").child("tag").getValue().toString();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.child("relation").getChildren()){
                    Log.d("This", "onDataChange: ");
                    String s= null;
                    s = dataSnapshot1.getValue().toString();
                    //while(s==null);
                    StudentId.add(s);
                }

                StudentDetail.clear();
                for(final String s : StudentId){

                    //student_id = databaseReferenceStudent.child(s).getKey().toString();
                    student_id = s;
                    databaseReference.child(s).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            userType = dataSnapshot.child("tag").child("tag").getValue().toString();
                            name = dataSnapshot.child("profile").child("name").getValue().toString();
                            email = dataSnapshot.child("profile").child("userEmail").getValue().toString();
                            String id2 = dataSnapshot.getKey().toString();
                            //while (name == null && email == null);
                            if(userType.equals("0")){
                                userType = "Student";
                            }else if(userType.equals("1")){
                                userType = "Investor";
                            }else{
                                userType = "Mentor";
                            }
                            //Toast.makeText(RelationPageInvestor.this, "in one relation " + name + " " + email, Toast.LENGTH_SHORT).show();
                            StudentDetail.add(new String[]{name, email, userType, id2});
                            Log.d("This", "onDataChange: 1");
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                //Toast.makeText(RelationPageInvestor.this, StudentDetail.size()+"", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onBackPressed() {
        finish();
    }
}