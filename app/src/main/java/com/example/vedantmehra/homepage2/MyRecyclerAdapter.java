package com.example.vedantmehra.homepage2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>   {

    //Glide GlideApp;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    Context context;
    ArrayList<String[]> arrayList;
    int index;
    Task<Uri> task;

    public MyRecyclerAdapter(Context context, ArrayList<String[]> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View)LayoutInflater.from(context).inflate(R.layout.recyclerview_row, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        View someView = viewHolder.view.findViewById(R.id.background);
        View root = someView.getRootView();
        if(i%2 == 0)
            root.setBackgroundColor(Color.parseColor("#c4ffbc"));
        TextView t1, t2, t3;
        Button chat, profile_visit;
        final ImageView profile_pic;
        profile_pic = viewHolder.view.findViewById(R.id.listImg);

        profile_visit = viewHolder.view.findViewById(R.id.visitProfileButton);
        t1 = viewHolder.view.findViewById(R.id.listName);
        t2 = viewHolder.view.findViewById(R.id.listEmail);
        t3 = viewHolder.view.findViewById(R.id.listType);
        t1.setText("Name - " + arrayList.get(i)[0]);
        t2.setText("Email - " + arrayList.get(i)[1]);
        t3.setText(arrayList.get(i)[2].toUpperCase());
        index = i;
        final String Studentid = arrayList.get(i)[3];


//        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//        storageRef.child("gs://authv4.appspot.com/0PinwKsrP7gPTOXkhZQevL18YJr2/Images/Profile Pic").getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//            @Override
//            public void onComplete(@NonNull Task<Uri> task) {
//                if(task.isSuccessful())
//                {
//                    String url = "https://firebasestorage.googleapis.com/v0/b/authv4.appspot.com/o/0PinwKsrP7gPTOXkhZQevL18YJr2%2FImages%2FProfile%20Pic?alt=media&token=cbb867b1-0da0-4acb-9fbb-29f610c6113d";//task.getResult().toString();
//                    Uri uri =  Uri.parse(url);
//                    Picasso.get().load(uri).fit().centerCrop().into(profile_pic);
//                }
//            }
//        });
        /*StorageReference filepath = storageReference.child("Photos").child("gs://relation-page-dynamic.appspot.com/Photos/sample_profile_image.png");
        filepath.putFile(Uri.parse("gs://relation-page-dynamic.appspot.com/Photos/sample_profile_image.png")).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> downloadUri = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                Picasso.with(getApplicationContext()).load("http://www.farsnews.com/shares/img/PLogo.jpg").into(profile_pic);
                //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(profile_pic);
            }

        });*/
        /*GlideApp.with(context)
                .load("https://firebasestorage.googleapis.com/v0/b/relation-page-dynamic.appspot.com/o/sample_profile_image.png?alt=media&token=79eda4d3-2e3a-4853-a7aa-739c5d0f78d2")
                .into(profile_pic);*/

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, profile_student_2.class);
                intent.putExtra("id", Studentid);
                context.startActivity(intent);
            }
        });


        profile_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, profile_student_2.class);
                intent.putExtra("id", Studentid);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }


}
