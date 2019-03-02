package com.example.supraja_pc.kiddiecura;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        if(nf!=null && nf.isConnected()) {
            auth = FirebaseAuth.getInstance();
            setContentView(R.layout.activity_login);
            final EditText email = findViewById(R.id.email);
            final EditText password = findViewById(R.id.password);
            TextView forgotpassword = findViewById(R.id.forgotpassword);
            Button login = findViewById(R.id.login);
            Button signup = findViewById(R.id.signup);


            final Intent intent = new Intent(Login.this, Main2Activity.class);
            progressDialog = new ProgressDialog(this);
            final FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
            if(currentuser!=null){
                final String currentemail = currentuser.getEmail().toString();
                startActivity(intent);
                finish();
            }
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String inputemail = email.getText().toString();
                    final String inputpassword = password.getText().toString();
                    if (TextUtils.isEmpty(inputemail)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(inputpassword)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    }

                    //authenticate user
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();
                    auth.signInWithEmailAndPassword(inputemail, inputpassword)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        progressDialog.dismiss();
                                        if (inputpassword.length() < 6) {
                                            password.setError(getString(R.string.min_password));
                                        } else {
                                            Toast.makeText(Login.this, getString(R.string.authentication_failed), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        progressDialog.dismiss();
                                        startActivity(intent);
                                        finish();

                                    }
                                }
                            });
                }
            });

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Signup.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
        else{
            Toast.makeText(Login.this,"Check your Internet Connectivity",Toast.LENGTH_SHORT).show();
        }
    }
    public void clicker(View v) {
        Intent i = new Intent(Login.this,Forgotpassword.class);
        startActivity(i);
        finish();
    }
}
