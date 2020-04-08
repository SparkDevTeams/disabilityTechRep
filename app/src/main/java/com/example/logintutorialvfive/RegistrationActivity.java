package com.example.logintutorialvfive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;
import android.content.*;

// added this vvv
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;


public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    private String name, password, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // initializes instance variables
        setupUIViews();

        // get instance of Firebase from registered user
        firebaseAuth = FirebaseAuth.getInstance();


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                if (validate()) {
                    // upload data to database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendEmailVerification();
                                //Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(RegistrationActivity.this, ThirdActivity.class));
                            }
                            else {
                                Log.e("ERROR", "--------------"+task.getException() + "--------------");
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    /**
     *
     */
    private void setupUIViews() {
        userName = findViewById(R.id.etUserName);
        userPassword = findViewById(R.id.etUserPassword);
        userEmail = findViewById(R.id.etUserEmail);
        regButton = findViewById(R.id.btnRegister);
        userLogin = findViewById(R.id.tvUserLogin);

    }

    /**
     *
     * @return
     */
    private boolean validate() {
        boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter required fields", Toast.LENGTH_SHORT).show();
        }
        else {

            int minIntCount = 2;
            int minUpperCaseLettersCount = 1;
            int minLength = 7;

            int intCount = 0;
            int upperCaseLetterCount = 0;
            int passwordLength = password.length();

            boolean intCountMet = false;
            boolean minUpperCaseLettersMet = false;
            boolean minLengthMet = false;

            boolean hasSpace = false;


            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    intCount++;
                }
                else if (Character.isUpperCase(password.charAt(i))) {
                    upperCaseLetterCount++;
                }
                else if (Character.isSpaceChar(password.charAt(i))) {
                    hasSpace = true;
                }
            }

            if (intCount >= minIntCount) {
                intCountMet = true;
            }
            if (upperCaseLetterCount >= minUpperCaseLettersCount) {
                minUpperCaseLettersMet = true;
            }
            if (passwordLength >= minLength) {
                minLengthMet = true;
            }




            if (!intCountMet) {
                Toast.makeText(this, "Password must have at least " + minIntCount + " digits", Toast.LENGTH_SHORT).show();
            }
            if (!minUpperCaseLettersMet) {
                Toast.makeText(this, "Password must have at least " + minUpperCaseLettersCount + " upper case character.", Toast.LENGTH_SHORT).show();
            }
            if (!minLengthMet) {
                Toast.makeText(this, "Password must have at least " + minLength + " characters.", Toast.LENGTH_SHORT).show();
            }
            if (hasSpace) {
                Toast.makeText(this, "Password can not have any spaces.", Toast.LENGTH_SHORT).show();
            }

            if (intCountMet && minUpperCaseLettersMet && minLengthMet && !hasSpace) {
                result = true;
            }
        }
        return result;
    }

    /**
     *  OPTIONAL
     */
    private void sendEmailVerification() {
        // don't need to get instance, this is new user trying to register
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this, "Successfully registered, verification email sent!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();

                        // added this v
                        //Intent intent = new Intent(RegistrationActivity.this, ThirdActivity.class);
                        //intent.putExtra("User_Name",name);
                        //startActivity(intent);

                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    }
                }
            });
        }
        else {
            Toast.makeText(RegistrationActivity.this, "Email verification has not been sent!",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendUserData() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid());
        //DatabaseReference myRef  = FirebaseDatabase.getInstance().getReference().child(name);
        //DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(name, email);
        myRef.setValue(userProfile);

        myRef = FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid()).child("Timed Sessions");
        myRef.child("Client 1").child("Session").push().setValue("00:00");

        myRef = FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid()).child("Timed Sessions");
        myRef.child("Client 2").child("Session").push().setValue("00:00");

        myRef = FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid()).child("Timed Sessions");
        myRef.child("Client 3").child("Session").push().setValue("00:00");



//        myRef.child("Client 2").push();
//        myRef.child("Client 3").push();
//
//        myRef.child("Session").push().setValue("00:00");



//        // before
//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid());
//        //DatabaseReference myRef  = FirebaseDatabase.getInstance().getReference().child(name);
//        //DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
//        UserProfile userProfile = new UserProfile(name, email);
//        myRef.setValue(userProfile);
//
//        myRef = FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid()).child("Timed Sessions");
//        //myRef.push().setValue(1);
//        myRef.child("Session").push().setValue("00:00");

    }
}
