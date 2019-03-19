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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * {@link Fragment} that displays a list of color vocabulary words.
 */
public class RequestFragment extends Fragment {

    DatabaseReference databaseReference, databaseReferenceNotification, databaseReferenceMentor, databaseReferenceInvestor;
    Word temp;
    //public int stat,pos;
    //learn set arguements and et argurments for the passing of status and pos and pass array instead
    public RequestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getUid());
        databaseReferenceNotification = databaseReference.child("notification");
        final ArrayList<Word> words = new ArrayList<Word>();
        final WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.tan_background);

        databaseReferenceNotification.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String,Object> data = (Map<String, Object>) dataSnapshot.getValue();
                String name = (String) data.get("name");
                int status = Integer.parseInt(data.get("status").toString());
                Log.d("check","name :" + name);
                Log.d("check","status :" + status);

                words.add(new Word("Friend Request", name, R.drawable.friendreq,
                        status));
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

        Log.d("check", "onCreateView: " + words.size());

        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                temp = words.get(position);
                if (temp.getRequestStatus() == 0) {
                    Intent intent = new Intent(getActivity(), RequestPage.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}