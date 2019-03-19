package com.example.vedantmehra.homepage2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.design.widget.NavigationView;

import com.google.firebase.auth.FirebaseAuth;


public class HomePage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private MenuItem prof, idea, relations;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Arqum
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        Intent intent = new Intent(HomePage.this, relation.class);
                        Toast.makeText(HomePage.this, "Relations", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_edit:
                        intent = new Intent(HomePage.this, NotificationMain.class);
                        Toast.makeText(HomePage.this, "Notification", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.action_remove:
                        intent = new Intent(HomePage.this, profile_student.class);
                        Toast.makeText(HomePage.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        //Arqum
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
                    Toast.makeText(HomePage.this, "My Profile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, profile_student.class);
                    startActivity(intent);

                }else if(id == R.id.relat){
                    Toast.makeText(HomePage.this, "My Relations", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, relation.class);
                    startActivity(intent);

                }else if(id == R.id.idea){
                    Toast.makeText(HomePage.this, "My Idea", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, idea.class);
                    startActivity(intent);

                }else if(id == R.id.logout){
                    Toast.makeText(HomePage.this, "Log Out", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(HomePage.this,MainActivity.class);
                    FirebaseAuth firebaseAuth;
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signOut();

                    // Kevin update the new page for the login page
                    startActivity(intent1);
                    finish();

                }else if(id == R.id.notif){
                    Toast.makeText(HomePage.this, "Notification Page", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, NotificationMain.class);
                    startActivity(intent);

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

}
