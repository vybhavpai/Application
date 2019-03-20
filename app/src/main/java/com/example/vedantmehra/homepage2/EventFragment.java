package com.example.vedantmehra.homepage2;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;


public class EventFragment extends Fragment {


    public EventFragment() {
        // Required empty public constructor
    }

    DatabaseReference databaseReference, databaseReferenceEvent;
    Word temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.word_list, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("events");


        final ArrayList<Word> words = new ArrayList<Word>();
        final WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.tan_background);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String,Object> data = (Map<String, Object>) dataSnapshot.getValue();
                String name = (String) data.get("eventName");
                String location = (String) data.get("location");

                words.add(new Word(name, "going to be held at " + location, R.drawable.event, 0));

                adapter.notifyDataSetChanged();
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
        ListView listView = (ListView) rootview.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                   Intent intent = new Intent(getActivity(), EventPage.class);
                    intent.putExtra("position", position);
                    startActivity(intent);

            }
        });

        return rootview;
    }



    @Override
    public void onStop() {
        super.onStop();
    }


}
