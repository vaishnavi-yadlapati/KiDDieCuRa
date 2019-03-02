package com.example.supraja_pc.kiddiecura;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.net.PasswordAuthentication;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;

public class Changepassword extends AppCompatActivity {

    private Button save;
    private EditText newp,confirm;

   // private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        progressDialog = new ProgressDialog(this);

        save = findViewById(R.id.save);
        newp = findViewById(R.id.newpassword);
        confirm = findViewById(R.id.confirm);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String newpassword = newp.getText().toString();
                final String confirmpassword = confirm.getText().toString();

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (TextUtils.isEmpty(newpassword) || TextUtils.isEmpty(confirmpassword) ) {
                    Toast.makeText(getApplicationContext(), "Enter all Details", Toast.LENGTH_SHORT).show();
                }

                else if (newpassword.equals(confirmpassword)) {

                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();


                    if(user!=null) {
                        assert newpassword!=null;
                        user.updatePassword(newpassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.setMessage("Password Changed Successfully");
                                            progressDialog.dismiss();
                                            Intent i = new Intent(Changepassword.this, Main2Activity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                        else{
                                            if (newpassword.length() < 6) {
                                                progressDialog.dismiss();
                                                Toast.makeText(Changepassword.this,"Password length should be greater than 6",Toast.LENGTH_SHORT).show();
                                            } else {
                                                progressDialog.dismiss();
                                                Toast.makeText(Changepassword.this, getString(R.string.authentication_failed), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                });
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Password Didnot Match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
