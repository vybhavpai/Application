package com.example.vedantmehra.homepage2;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegistrationInvestor extends AppCompatActivity {

    private EditText userSchool, userDegree, userBranch, userGraduation;
    private EditText userCompany, userOccupation;
    private EditText userArea, userCity, userState, userCountry;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    //    private ImageView userProfilePic;
    private Uri imagePath;
    private String email, name, password, userType;
    private String school, degree, graduation, branch;
    private String area, city, state, country;
    private String occupation, company;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    private StorageReference storageReference;

    private RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_investor);
        setTitle("Sign up");

        // TODO get bundle here
        Bundle extras = getIntent().getExtras();
        imagePath = Uri.parse(getIntent().getStringExtra("IMAGEPATH"));
        name = extras.getString("USERNAME");
        email = extras.getString("EMAIL");
        password = extras.getString("PASSWORD");
        userType = extras.getString("USERTYPE");

        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

//        userProfilePic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 2);
//            }
//        });


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = email;
                    String user_password = password;

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegistrationInvestor.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationInvestor.this, MainActivity.class));
                            }else{
                                Toast.makeText(RegistrationInvestor.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationInvestor.this, MainActivity.class));
            }
        });
    }

    private void setupUIViews(){
        userOccupation = (EditText)findViewById(R.id.etOccupation);
        userCompany = (EditText)findViewById(R.id.etCompany);

        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);

        userArea = (EditText)findViewById(R.id.etArea);
        userCity = (EditText)findViewById(R.id.etCity);
        userState = (EditText)findViewById(R.id.etState);
        userCountry = (EditText)findViewById(R.id.etCountry);
    }

    private Boolean validate(){
        Boolean failFlag = false;
        if (!validateOccupation())
            failFlag = true;
        if (!validateCompany())
            failFlag = true;
        if (!validateArea())
            failFlag = true;
        if (!validateCity())
            failFlag = true;
        if (!validatesState())
            failFlag = true;
        if (!validateCountry())
            failFlag = true;

        if (failFlag) {
            return false;
        }
        return true;
    }

    private boolean validateOccupation() {
        occupation = userOccupation.getText().toString().trim();

        if (occupation.isEmpty()) {
            userOccupation.setError("Field can't be empty");
            return false;
        } else {
            userOccupation.setError(null);
            return true;
        }
    }

    private boolean validateCompany() {
        company = userCompany.getText().toString().trim();

        if (company.isEmpty()) {
            userCompany.setError("Field can't be empty");
            return false;
        } else {
            userCompany.setError(null);
            return true;
        }
    }

    private boolean validateArea() {
        area = userArea.getText().toString().trim();

        if (area.isEmpty()) {
            userArea.setError("Field can't be empty");
            return false;
        } else {
            userArea.setError(null);
            return true;
        }
    }

    private boolean validateCity() {
        city = userCity.getText().toString().trim();

        if (city.isEmpty()) {
            userCity.setError("Field can't be empty");
            return false;
        } else {
            userCity.setError(null);
            return true;
        }
    }

    private boolean validatesState() {
        state = userState.getText().toString().trim();

        if (state.isEmpty()) {
            userState.setError("Field can't be empty");
            return false;
        } else {
            userState.setError(null);
            return true;
        }
    }

    private boolean validateCountry() {
` `        country = userCountry.getText().toString().trim();

        if (country.isEmpty()) {
            userCountry.setError("Field can't be empty");
            return false;
        } else {
            userCountry.setError(null);
            return true;
        }
    }


    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("user");
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationInvestor.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(RegistrationInvestor.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });
        UserProfile userProfile = new UserProfile(name, email,2 , occupation, company,
                "", "", "", "",
                area, city, state, country);
        myRef.child(firebaseAuth.getUid()).child("profile").setValue(userProfile);
        Tag tag = new Tag(userType);
        myRef.child(firebaseAuth.getUid()).child("tag").setValue(tag);
    }
}


/*import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegistrationInvestor extends AppCompatActivity {

    private EditText userSchool, userDegree, userBranch, userGraduation;
    private EditText userCompany, userOccupation;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    //    private ImageView userProfilePic;
    private Uri imagePath;
    private String email, name, age, password, bio;
    private String school, degree, graduation, branch;
    private String occupation, company;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    private StorageReference storageReference;

    private RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_investor);

        // TODO get bundle here
        Bundle extras = getIntent().getExtras();
        imagePath = Uri.parse(getIntent().getStringExtra("IMAGEPATH"));
        bio = extras.getString("BIO", "Hello there General");
        name = extras.getString("NAME", "Kevin Joe");
        email = extras.getString("EMAIL");
        age = extras.getString("AGE", "19");
        password = extras.getString("PASSWORD", "a!123456789");

        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

//        userProfilePic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 2);
//            }
//        });


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = email;
                    String user_password = password;

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegistrationInvestor.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationInvestor.this, MainActivity.class));
                            }else{
                                Toast.makeText(RegistrationInvestor.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationInvestor.this, MainActivity.class));
            }
        });
    }

    private void setupUIViews(){
        userOccupation = (EditText)findViewById(R.id.etOccupation);
        userCompany = (EditText)findViewById(R.id.etCompany);

        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
    }

    private Boolean validate(){
        if (validateOccupation() && validateCompany()) {
            return true;
        }
        return false;
    }

    private boolean validateOccupation() {
        occupation = userOccupation.getText().toString().trim();

        if (occupation.isEmpty()) {
            userOccupation.setError("Field can't be empty");
            return false;
        } else {
            userOccupation.setError(null);
            return true;
        }
    }

    private boolean validateCompany() {
        company = userCompany.getText().toString().trim();

        if (company.isEmpty()) {
            userCompany.setError("Field can't be empty");
            return false;
        } else {
            userCompany.setError(null);
            return true;
        }
    }


    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationInvestor.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(RegistrationInvestor.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });
        UserProfile userProfile = new UserProfile("12", email, name);
        myRef.setValue(userProfile);
    }
}*/
