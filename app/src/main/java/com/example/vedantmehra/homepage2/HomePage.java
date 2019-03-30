package com.example.vedantmehra.homepage2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.NavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;


public class HomePage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private MenuItem prof, idea, relations;


    private Button mSubmitButton;
    private EditText mInputText;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef,mEventRef;
    ArrayList<String> myArrayList = new ArrayList<>();
    ArrayList<String> secondArrayList = new ArrayList<>();
//    ArrayList<Profile> profileObjList = new ArrayList<>();
//    ArrayList<TagClass> tagObjList = new ArrayList<>();
    ArrayList<Event> eventList = new ArrayList<>();
    ArrayList<Integer> flag = new ArrayList<>();
    ArrayList<String> keyList = new ArrayList<>();
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Vedant
        DatabaseReference databaseReference;
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // Vedant
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        final String[] userType = {""};
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.child(currentUserId).child("tag").child("tag").getValue().toString();
                if(temp.equals("0")){
                    Intent intent = new Intent(HomePage.this, HomePageStudent.class);
                    startActivity(intent);
                    finish();
                }else if(temp.equals("2")){
                    Intent intent = new Intent(HomePage.this, HomePageMentor.class);
                    startActivity(intent);
                    finish();
                }
                userType[0] = temp;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        while(userType[0].equals(null));
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if(userType[0].equals("0")){
            Intent intent = new Intent(HomePage.this, HomePageStudent.class);
            startActivity(intent);
            finish();
        }else if(userType[0].equals("2")){
            Intent intent = new Intent(HomePage.this, HomePageMentor.class);
            startActivity(intent);
            finish();
        }






        mDatabase = FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("user");
        mEventRef = mDatabase.getReference("events");
        mSubmitButton = findViewById(R.id.button);
        mInputText = findViewById(R.id.search);

        myListView = (ListView)findViewById(R.id.list_view);


        this.mSubmitButton.setOnClickListener(this::readData);
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
                else if(id == R.id.wallet){
                    if(userType[0].equals("0")){
                        Toast.makeText(HomePage.this, "Wallet", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomePage.this, WalletPageStudent.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(HomePage.this, "Wallet", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomePage.this, WalletPageInvestor.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });

    }

    public class ToProfile implements Serializable {
        TagClass tagObj;
        Profile profileObj;

    }
    public class ToEvent implements Serializable{
        Event eventObj;
    }
    private void readData(View view)
    {
        //ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);
        final Adapter adapter=new Adapter();
        myListView.setAdapter(adapter);
        myArrayList.clear();
        secondArrayList.clear();
//        profileObjList.clear();
//        tagObjList.clear();
        flag.clear();
        eventList.clear();
        keyList.clear();
        final String data = mInputText.getText().toString();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //Map<String,Object> data = (Map<String,Object>);
                    Profile obj = new Profile();
                    obj=snapshot.child("profile").getValue(Profile.class);
                    String val = obj.name;
                    String value="OnDataChange: Name: "+val;
                    String TAG="tag";
                    TagClass obj1 = new TagClass();
                    //obj1 = snapshot.child("tag").getValue(Tag.class);
                    String ob=snapshot.child("tag").child("tag").getValue(String.class);
                    obj1.tagString=ob;
                    System.out.println(val);
                    if(data.toLowerCase().contains(val.toLowerCase()))
                    {
                        Toast.makeText(getApplicationContext(),val+" "+data,Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                        keyList.add(snapshot.getKey());
                        myArrayList.add(val);
                        Log.d(TAG,val);
                        String s = obj1.tagString;
                        if(s.equals("0"))
                            secondArrayList.add("Student");
                        else if(s.equals("1"))
                            secondArrayList.add("Investor");
                        else
                            secondArrayList.add("Mentor");
//                        profileObjList.add(obj);
//                        tagObjList.add(obj1);
                        flag.add(0);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();            }

        });
        mEventRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                myArrayList.clear();
//                secondArrayList.clear();
//                profileObjList.clear();
//                tagObjList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    Event obj = new Event();
                    obj = snapshot.getValue(Event.class);
                    String value = obj.eventName;
                    if(data.toLowerCase().contains(value.toLowerCase()))
                    {
                        Toast.makeText(getApplicationContext(),"events-- "+value+" "+data,Toast.LENGTH_LONG).show();

                        myArrayList.add(value);
                        secondArrayList.add(obj.date);
                        flag.add(1);
                        eventList.add(obj);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.search_list_element,parent,false);
            TextView fuln=convertView.findViewById(R.id.full_name);
            fuln.setText(myArrayList.get(position));
            TextView var = convertView.findViewById(R.id.second);
            var.setText(secondArrayList.get(position));
            LinearLayout lView = (LinearLayout) convertView.findViewById(R.id.search_list);
            lView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    Bundle bundle = new Bundle();
                    if(flag.get(position)==0) {
                        Intent intent = new Intent(HomePage.this,profile_student_1.class);
                        ToProfile profileTagObj = new ToProfile();
//                        profileTagObj.profileObj = profileObjList.get(position);
//                        profileTagObj.tagObj = tagObjList.get(position);
                        //Add your data from getFactualResults method to bundle
                        //bundle.putSerializable("NAME", profileTagObj);
                        intent.putExtra("id",keyList.get(position));
                        Toast.makeText(HomePage.this, "hello", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
//Add the bundle to the intent
                    }
                    else{
                        int pos = position-keyList.size();
                        Intent intent = new Intent(HomePage.this,EventPage.class);
                        ToEvent EventObj = new ToEvent();
                        EventObj.eventObj=eventList.get(pos);
                        bundle.putString("EVENT_NAME",eventList.get(pos).eventName);
                        bundle.putString("DESCRIPTION",eventList.get(pos).description);
                        bundle.putString("VENUE",eventList.get(pos).location);
                        bundle.putString("DATE",eventList.get(pos).date);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                }
            });
            return convertView;
        }
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
