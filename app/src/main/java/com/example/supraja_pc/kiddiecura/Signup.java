package com.example.supraja_pc.kiddiecura;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;

public class Signup extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        if(nf!=null && nf.isConnected()) {
            Button button = findViewById(R.id.signupbutton);
            final EditText email = findViewById(R.id.email);
            final EditText password = findViewById(R.id.password);
            final EditText cpassword = findViewById(R.id.cpassword);
            progressDialog = new ProgressDialog(this);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || cpassword.getText().toString().isEmpty()) {
                        Toast.makeText(Signup.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.getText().toString().equals(cpassword.getText().toString())) {

                            final String user_email = email.getText().toString().trim();
                            final String user_password = password.getText().toString().trim();
                            firebaseAuth.fetchProvidersForEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                                @Override
                                public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                                    boolean check = !task.getResult().getProviders().isEmpty();
                                    if(!check){
                                        progressDialog.setMessage("Registering Please Wait...");
                                        progressDialog.show();
                                        firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(Signup.this, Login.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    Log.d("hello","error");
                                                    progressDialog.dismiss();
                                                    Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                    else{
                                        Toast.makeText(Signup.this,"Email already exists",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        } else {
                            Toast.makeText(Signup.this, "Confirm Password did not match with Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }
        else {
            Toast.makeText(Signup.this,"Check your Internet Connectivity",Toast.LENGTH_SHORT).show();
        }
    }
}
