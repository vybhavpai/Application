package com.example.vedantmehra.homepage2;



import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.sax.StartElementListener;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class  RegistrationActivity extends AppCompatActivity{

    private EditText userName, userPassword, userEmail, userBio;
    private Button regButton;
    private Button nextButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    private String email, name, age, password, bio;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    private Uri imagePath;
    private StorageReference storageReference;

    private RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data.getData() != null) {
            imagePath = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query (imagePath, filePath, null, null, null);
            c.moveToFirst ();
            int columnIndex = c.getColumnIndex (filePath[0]);
            String picturePath = c.getString (columnIndex);
            c.close ();
            Bitmap thumbnail = (BitmapFactory.decodeFile (picturePath));
            Log.w ("pery", picturePath + "");
            userProfilePic.setImageBitmap (thumbnail);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Sign up");
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        userProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });


//        regButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(validate()){
//                    //Upload data to the database
//                    String user_email = userEmail.getText().toString().trim();
//                    String user_password = userPassword.getText().toString().trim();
//
//                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                            if(task.isSuccessful()){
//                                //sendEmailVerification();
//                                sendUserData();
//                                firebaseAuth.signOut();
//                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
//                                finish();
//                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//                            }else{
//                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });
//                }
//            }
//        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            //            Toast.makeText(RegistrationActivity.this, "ALL GOOD", Toast.LENGTH_SHORT).show();
            @Override
            public void onClick(View v) {
               if (validate()) {
                    // TODO make the intent here and pass the variables here
//                     Toast.makeText(RegistrationActivity.this, "ALL GOOD", Toast.LENGTH_SHORT).show();

                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);

                    if (radioButton.getText().toString().trim().equals("Student")) {
                        Toast.makeText(RegistrationActivity.this, "STUDENT", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrationActivity.this, RegistrationStudent.class);
                        Bundle extras = new Bundle();

                        intent.putExtra("IMAGEPATH", imagePath.toString());
                        extras.putString("USERNAME", name);
                        extras.putString("PASSWORD", password);
                        extras.putString("EMAIL", email);
                        extras.putString("USERTYPE", "0");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }

                    // TODO make activity for investor
                    if (radioButton.getText().toString().trim().equals("Investor")) {
                        Toast.makeText(RegistrationActivity.this, "INVESTOR", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrationActivity.this, RegistrationInvestor.class);
                        Bundle extras = new Bundle();

                        intent.putExtra("IMAGEPATH", imagePath.toString());
                        extras.putString("USERNAME", name);
                        extras.putString("EMAIL", email);
                        extras.putString("PASSWORD", password);
                        extras.putString("USERTYPE", "1");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }

                    if (radioButton.getText().toString().trim().equals("Mentor")) {
                        Toast.makeText(RegistrationActivity.this, "INVESTOR", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrationActivity.this, RegistrationInvestor.class);
                        Bundle extras = new Bundle();

                        intent.putExtra("IMAGEPATH", imagePath.toString());
                        extras.putString("USERNAME", name);
                        extras.putString("EMAIL", email);
                        extras.putString("PASSWORD", password);
                        extras.putString("USERTYPE", "2");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }

                } else {
//                     Toast.makeText(RegistrationActivity.this, "BAD", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupUIViews(){
        userProfilePic = (ImageView)findViewById(R.id.ivProfile);

        userName = (EditText)findViewById(R.id.etUserName);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        radioGroup = findViewById(R.id.rgUser);

        nextButton = (Button)findViewById(R.id.btnNext);

        userLogin = (TextView)findViewById(R.id.tvUserLogin);
    }

    // change validate accordingly
    private Boolean validate(){
        Boolean failFlag = false;
        if (!validateName())
            failFlag = true;
        if (!validatePassword())
            failFlag = true;
        if (!validateEmail())
            failFlag = true;
//        if (!validateBio())
//            failFlag = true;
//        if (!validateProfilePicture())
//            failFlag = true;

        if (failFlag) {
            return false;
        }
        return true;
    }

    private boolean validateProfilePicture() {
        if (!Uri.EMPTY.equals(imagePath)) {
            imagePath = Uri.parse("android://com.example.authv4/mipmap/ic_launcher");
        }
        return true;
    }

    private boolean validateName() {
        name = userName.getText().toString().trim();

        if (name.isEmpty()) {
            userName.setError("Field cannot be empty");
            return false;
        } else if (userName.length() > 15) {
            userName.setError("Name cannot be greater than 15");
            return false;
        } else {
            userName.setError(null);
            return true;
        }
    }


    private boolean validateEmail() {
        email = userEmail.getText().toString().trim();

        if (email.isEmpty()) {
            userEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.setError("Please enter a valid email address");
            return false;
        } else {
            userEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        //"(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[!@#$%^&+=*])" +    //at least 1 special character
//                        "(?=\\S+$)" +           //no white spaces
                        ".{8,}" +               //at least 8 characters
                        "$");

        password = userPassword.getText().toString().trim();
        if (password.isEmpty()) {
            userPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            userPassword.setError("Password should have any letter, 1 special character, at least 8 letters");
            return false;
        } else {
            userPassword.setError(null);
            return true;
        }
    }

    private boolean validateBio() {
        bio = userBio.getText().toString().trim();
        if (bio.isEmpty()) {
            userBio.setError("Field can't be empty");
            return false;
        } else if (bio.length() > 100){
            userBio.setError("Bio can't be greater than 100 characters");
            return false;
        }
        else {
            userBio.setError(null);
            return true;
        }
    }





//    private void sendEmailVerification(){
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if(firebaseUser!=null){
//            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        sendUserData();
//                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
//                        firebaseAuth.signOut();
//                        finish();
//                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//                    }else{
//                        Toast.makeText(RegistrationActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }

//    private void sendUserData(){
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
//        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
//        UploadTask uploadTask = imageReference.putFile(imagePath);
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(RegistrationActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                Toast.makeText(RegistrationActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        UserProfile userProfile = new UserProfile(age, email, name);
//        myRef.setValue(userProfile);
//    }
}



/*import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.sax.StartElementListener;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class  RegistrationActivity extends AppCompatActivity{

    private EditText userName, userPassword, userEmail, userBio;
    private Button regButton;
    private Button nextButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    private String email, name, age, password, bio;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    private Uri imagePath;
    private StorageReference storageReference;

    private RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data.getData() != null) {
            imagePath = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query (imagePath, filePath, null, null, null);
            c.moveToFirst ();
            int columnIndex = c.getColumnIndex (filePath[0]);
            String picturePath = c.getString (columnIndex);
            c.close ();
            Bitmap thumbnail = (BitmapFactory.decodeFile (picturePath));
            Log.w ("pery", picturePath + "");
            userProfilePic.setImageBitmap (thumbnail);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        userProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });


//        regButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(validate()){
//                    //Upload data to the database
//                    String user_email = userEmail.getText().toString().trim();
//                    String user_password = userPassword.getText().toString().trim();
//
//                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                            if(task.isSuccessful()){
//                                //sendEmailVerification();
//                                sendUserData();
//                                firebaseAuth.signOut();
//                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
//                                finish();
//                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//                            }else{
//                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });
//                }
//            }
//        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            //            Toast.makeText(RegistrationActivity.this, "ALL GOOD", Toast.LENGTH_SHORT).show();
            @Override
            public void onClick(View v) {
                if (validate()) {
                    // TODO make the intent here and pass the variables here
//                     Toast.makeText(RegistrationActivity.this, "ALL GOOD", Toast.LENGTH_SHORT).show();

                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);

                    if (radioButton.getText().toString().trim().equals("Student")) {
                        Toast.makeText(RegistrationActivity.this, "STUDENT", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrationActivity.this, RegistrationStudent.class);
                        Bundle extras = new Bundle();

                        intent.putExtra("IMAGEPATH", imagePath.toString());
                        extras.putString("BIO", bio);
                        extras.putString("USERNAME", name);
                        extras.putString("EMAIL", email);
                        extras.putString("PASSWORD", password);
                        extras.putString("USERTYPE", "student");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }

                    // TODO make activity for investor
                    if (radioButton.getText().toString().trim().equals("Investor")) {
                        Toast.makeText(RegistrationActivity.this, "INVESTOR", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrationActivity.this, RegistrationInvestor.class);
                        Bundle extras = new Bundle();

                        intent.putExtra("IMAGEPATH", imagePath.toString());
                        extras.putString("BIO", bio);
                        extras.putString("USERNAME", name);
                        extras.putString("EMAIL", email);
                        extras.putString("PASSWORD", password);
                        extras.putString("USERTYPE", "investor");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(RegistrationActivity.this, "BAD", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupUIViews(){
        userProfilePic = (ImageView)findViewById(R.id.ivProfile);

        userName = (EditText)findViewById(R.id.etUserName);
        userBio = (EditText)findViewById(R.id.etBio);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        radioGroup = findViewById(R.id.rgUser);

        nextButton = (Button)findViewById(R.id.btnNext);

        userLogin = (TextView)findViewById(R.id.tvUserLogin);
    }

    // change validate accordingly

    private Boolean validate(){
        if (validateName() && validateBio() && validateName() && validateEmail()  && validatePassword()) {
            return true;
        }
        return false;
    }

    private boolean validateName() {
        name = userName.getText().toString().trim();

        if (name.isEmpty()) {
            userName.setError("Field cannot be empty");
            return false;
        } else if (userName.length() > 15) {
            userName.setError("Name cannot be greater than 15");
            return false;
        } else {
            userName.setError(null);
            return true;
        }
    }


    private boolean validateEmail() {
        email = userEmail.getText().toString().trim();

        if (email.isEmpty()) {
            userEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.setError("Please enter a valid email address");
            return false;
        } else {
            userEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        //"(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[!@#$%^&+=*])" +    //at least 1 special character
//                        "(?=\\S+$)" +           //no white spaces
                        ".{8,}" +               //at least 8 characters
                        "$");

        password = userPassword.getText().toString().trim();
        if (password.isEmpty()) {
            userPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            userPassword.setError("Password should have any letter, 1 special character, at least 8 letters");
            return false;
        } else {
            userPassword.setError(null);
            return true;
        }
    }

    private boolean validateBio() {
        bio = userBio.getText().toString().trim();
        if (bio.isEmpty()) {
            userBio.setError("Field can't be empty");
            return false;
        } else if (bio.length() > 100){
            userBio.setError("Bio can't be greater than 100 characters");
            return false;
        }
        else {
            userBio.setError(null);
            return true;
        }
    }





//    private void sendEmailVerification(){
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if(firebaseUser!=null){
//            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        sendUserData();
//                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
//                        firebaseAuth.signOut();
//                        finish();
//                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//                    }else{
//                        Toast.makeText(RegistrationActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(RegistrationActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });
        UserProfile userProfile = new UserProfile(age, email, name);
        myRef.setValue(userProfile);
    }
}*/


