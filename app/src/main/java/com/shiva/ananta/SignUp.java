package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {

    TextView continuebutton,skipbutton,resendotp,number;

    LinearLayout layout;
    EditText verify_enterotp;
    int RC_SIGN_IN = 40;
    FrameLayout Signupgoogle;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    GoogleSignInClient googleSignInClient;
    ProgressDialog progressDialog;
    ProgressBar progessbar;
    View loadingbar;
    EditText inputno1,inputno2,inputno3,inputno4,inputno5,inputno6;
    EditText signin_fullName,signin_phoneno,signin_emial,signin_password,signin_confirmpassword,signup_enterotp;
    @SuppressLint({"MissingInflatedId", "CutPasteId", "WrongViewCast", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        continuebutton = findViewById(R.id.continuebutton);
        skipbutton = findViewById(R.id.skipbutton);
        loadingbar = findViewById(R.id.loadingbar);

        resendotp = findViewById(R.id.resendotp);
        inputno1 = findViewById(R.id.otp1);
        inputno2 = findViewById(R.id.otp2);
        inputno3 = findViewById(R.id.otp3);
        inputno4 = findViewById(R.id.otp4);
        inputno5 = findViewById(R.id.otp5);
        inputno6 = findViewById(R.id.otp6);
        number = findViewById(R.id.number);

        progessbar = findViewById(R.id.progessbar);

        String data = getIntent().getStringExtra("mobile");
        number.setText(data);

        String mobileotp = getIntent().getStringExtra("mobileotp");


        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog  = new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("we are creating your account");


        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingbar.setVisibility(View.VISIBLE);
                if(!inputno1.getText().toString().trim().isEmpty() && !inputno2.getText().toString().trim().isEmpty() && !inputno3.getText().toString().trim().isEmpty()
                        && !inputno4.getText().toString().trim().isEmpty()
                && !inputno5.getText().toString().trim().isEmpty() &&!inputno6.getText().toString().trim().isEmpty()){

                    String entercodeotp = inputno1.getText().toString()+
                            inputno2.getText().toString()+
                            inputno3.getText().toString()+
                            inputno4.getText().toString()+
                            inputno5.getText().toString()+
                            inputno6.getText().toString();

                    if(mobileotp!=null){
                        loadingbar.setVisibility(View.VISIBLE);
//                        progessbar.setVisibility(View.VISIBLE);
                        continuebutton.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                mobileotp,entercodeotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(
                                        new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                loadingbar.setVisibility(View.VISIBLE);
                                                continuebutton.setVisibility(View.VISIBLE);
                                                if(task.isSuccessful()){
                                                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                                                    SharedPreferences sh = getSharedPreferences("login", Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sh.edit();
                                                    editor.putBoolean("flag", true);
                                                    editor.apply();
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                   startActivity(intent);
                                                    loadingbar.setVisibility(View.GONE);
                                                }else {
                                                    Toast.makeText(SignUp.this, "Enter the correct otp!", Toast.LENGTH_SHORT).show();
                                                    loadingbar.setVisibility(View.GONE);
                                                }
                                            }
                                        }
                                );
                    }else {
                        Toast.makeText(SignUp.this, "please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(SignUp.this, "please enter all number", Toast.LENGTH_SHORT).show();
                }
                loadingbar.setVisibility(View.GONE);
            }
        });

        numberotpmove();


        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingbar.setVisibility(View.VISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" +data,
                        60,
                        TimeUnit.SECONDS,
                        SignUp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                handleVerificationCompleted(phoneAuthCredential);
                                loadingbar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                handleVerificationFailed(e);
                                loadingbar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                handleCodeSent(s);
                                loadingbar.setVisibility(View.GONE);
                            }
                        });
            }
        });

        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivities(new Intent[]{intent});
            }
        });

    }

    private void numberotpmove() {
        inputno1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(!s.toString().trim().isEmpty()){
                   inputno2.requestFocus();
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        inputno2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputno3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputno3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputno4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputno4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputno5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputno5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputno6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void handleVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

    }
    private void handleVerificationFailed(FirebaseException e) {
        Toast.makeText(SignUp.this, "Verification Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    private void handleCodeSent(String verificationId) {
    }
}