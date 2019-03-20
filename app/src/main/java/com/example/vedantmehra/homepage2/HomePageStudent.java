package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomePageStudent extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private MenuItem prof, idea, relations;
    DatabaseReference databaseReference;

    String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_student);


        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        final String[] userType = {""};
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.child(currentUserId).child("tag").child("tag").getValue().toString();
                userType[0] = temp;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        Intent intent = new Intent(HomePageStudent.this, relation.class);
                        Toast.makeText(HomePageStudent.this, "Relations", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_edit:
                        intent = new Intent(HomePageStudent.this, notification.class);
                        Toast.makeText(HomePageStudent.this, "Notification", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_remove:
                        intent = new Intent(HomePageStudent.this, profile_student.class);
                        Toast.makeText(HomePageStudent.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView nav_view = (NavigationView)findViewById(R.id.navView);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                // Intent intent = new Intent(HomePage.this, NewPage.class);

                if(id == R.id.myprof){
                    Toast.makeText(HomePageStudent.this, "My Profile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePageStudent.this, profile_student.class);
                    startActivity(intent);

                }else if(id == R.id.relat){
                    Toast.makeText(HomePageStudent.this, "My Relations", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePageStudent.this, relation.class);
                    startActivity(intent);

                }else if(id == R.id.idea){
                    Toast.makeText(HomePageStudent.this, "My Idea", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePageStudent.this, com.example.vedantmehra.homepage2.idea.class);
                    startActivity(intent);

                }else if(id == R.id.logout){
                    Toast.makeText(HomePageStudent.this, "Log Out", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(HomePageStudent.this,MainActivity.class);
                    FirebaseAuth firebaseAuth;
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signOut();

                    // Kevin update the new page for the login page
                    startActivity(intent1);
                    finish();

                }else if(id == R.id.notif){
                    Toast.makeText(HomePageStudent.this, "Notification Page", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePageStudent.this, notification.class);
                    startActivity(intent);

                }
                else if(id == R.id.wallet){
                    if(userType[0].equals("0")){
                        Toast.makeText(HomePageStudent.this, "Wallet", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomePageStudent.this, WalletPageStudent.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(HomePageStudent.this, "Wallet", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomePageStudent.this, WalletPageInvestor.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });

    }


    void clicked(View view)
    {
        startActivity(new Intent(this,NewPage.class));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) ||  super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
