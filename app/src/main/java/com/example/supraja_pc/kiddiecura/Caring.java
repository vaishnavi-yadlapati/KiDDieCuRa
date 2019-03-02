package com.example.supraja_pc.kiddiecura;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Caring extends AppCompatActivity {
    private EditText name,contact,salary,location,timings,kids;
    Button submit;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caring);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.phone);
        location = findViewById(R.id.location);
        salary = findViewById(R.id.salary);
        timings = findViewById(R.id.timings);
        kids = findViewById(R.id.kids);
        submit = findViewById(R.id.submit);
        progressDialog = new ProgressDialog(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
                final FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
                final String useremail = currentuser.getEmail().toString();
                final String uid = FirebaseAuth.getInstance().getUid();
                final String username = name.getText().toString();
                final String usercontact = contact.getText().toString();
                final String userlocation = location.getText().toString();
                final String usersalary = salary.getText().toString();
                final String usertimings = timings.getText().toString();
                final String userkids = kids.getText().toString();

// Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();



                HashMap<String, Object> details = new HashMap<>();
                details.put("Name",username);
                details.put("Email",useremail);
                details.put("Contact",usercontact);
                details.put("Location",userlocation);
                details.put("Salary",usersalary);
                details.put("Timings",usertimings);
                details.put("Kids",userkids);

                myRef.child("CareTakers").child(uid).setValue(details)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Toast.makeText(Caring.this,"Successful",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Caring.this,Main2Activity.class);
                                startActivity(i);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(Caring.this,"Error on Submitting Details",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}
