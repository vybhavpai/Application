package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapTemp extends AppCompatActivity {

    DatabaseReference databaseReference, databaseReferenceEvent;
    Word temp;
    int dist;
    double lat1,lat2,lon1,lon2,distance;
    String locate = "";
    int pos;
    String city;
    ArrayList<Word> words = new ArrayList<Word>();
    final ArrayList<String> url = new ArrayList<>();
    WordAdapter adapter;
    String name ,location ,description ,date ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        WordAdapter adapterlocal = new WordAdapter(this, words, R.color.tan_background);
        adapter = adapterlocal;




        databaseReference = FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getUid());
        databaseReference = databaseReference.child("profile");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().equals("city"))
                {
                    String temp = dataSnapshot.getValue().toString();
                    GeocodingLocation locationAddress = new GeocodingLocation();
                    locationAddress.getAddressFromLocation(temp,
                            getApplicationContext(), new Longitude1());
                    locationAddress.getAddressFromLocation(temp,
                            getApplicationContext(), new Latitude1());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("events");
        databaseReference.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Map<String,Object> data = (Map<String, Object>) dataSnapshot.getValue();
            name = (String) data.get("eventName");
            location = (String) data.get("location");
            description = (String) data.get("description");
            date = (String) data.get("date");
            String temp = data.get("url").toString();
            GeocodingLocation locationAddress = new GeocodingLocation();
            locationAddress.getAddressFromLocation(location,
                    getApplicationContext(), new Longitude2());
            locationAddress.getAddressFromLocation(location,
                    getApplicationContext(), new Latitude2(name,location,description,date,temp));

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    // word_list.xml layout file.
    ListView listView = (ListView) findViewById(R.id.list);

    // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
    // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

    // Set a click listener to play the audio when the list item is clicked on

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            Intent intent = new Intent(MapTemp.this, EventPage.class);
            Bundle bundle = new Bundle();
            // Event sending = new Event();
            Word object = words.get(position);
//                    sending.date = object.date;
//                    sending.description = object.description;
//                    sending.eventName = object.getHeader();
//                    sending.location = object.getSubHeader();
//                    event.putSerializable("EVENT_NAME",sending);
            bundle.putString("EVENT_NAME",object.getHeader());
            bundle.putString("DESCRIPTION",object.description);
            bundle.putString("VENUE",object.getSubHeader());
            bundle.putString("DATE",object.date);
            bundle.putString("URL",url.get(position));
            intent.putExtras(bundle);

            startActivity(intent);

        }
    });


}

    private class Latitude1 extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

            locate = locationAddress;
            if(!(locate.charAt(0) == 'U')) {
                pos = position(locate);
                lat1 = Double.parseDouble(locate.substring(0, pos - 1));
            }
        }
    }

    private class Longitude1 extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

            locate = locationAddress;
            if(!(locate.charAt(0) == 'U')) {
                pos = position(locate);
                lon1 = Double.parseDouble(locate.substring(pos + 1, locate.length() - 1));
            }
        }
    }

    private class Latitude2 extends Handler {
        String name ,location ,description ,date,Url ;
        Latitude2(String name ,String location ,String description ,String date,String temp ){
            super();
            this.date = date;
            this.description = description;
            this.location = location;
            this.name = name;
            this.Url = temp;
        }

        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

            locate = locationAddress;
            if(!(locate.charAt(0) == 'U')) {
                pos = position(locate);
                lat2 = Double.parseDouble(locate.substring(0, pos - 1));
                Log.d("abcde", "lat1  : " + lat1 + "lon1  : " + lon1 + "lat2  : " + lat2 + "lon2  : " + lon2);
                distance = 2 * distance(lat1, lon1, lat2, lon2);
                Log.d("abcde", "" + distance);
                if (distance <= 500) {
                    words.add(new Word(date, description, name, "going to be held at " + location, R.drawable.event, 0));
                    url.add(Url);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    private class Longitude2 extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

            locate = locationAddress;
            if(!(locate.charAt(0) == 'U')) {
                pos = position(locate);
                lon2 = Double.parseDouble(locate.substring(pos + 1, locate.length() - 1));
            }
           }
    }

    int position(String location)
    {
        int i;
        for(i = 0;i<location.length();i++)
            if(location.charAt(i) == '\n')
                break;
        Log.d("position","" + i);
        return i;
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    @Override
    public void onStop() {
        super.onStop();
    }


}


