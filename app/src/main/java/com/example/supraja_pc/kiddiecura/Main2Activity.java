package com.example.supraja_pc.kiddiecura;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    private ImageView caring = null;
    private ImageView picking = null;
    private ImageView tuitions = null;
    private ImageView nursing = null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        caring = findViewById(R.id.caring);
        picking = findViewById(R.id.picking);
        tuitions = findViewById(R.id.tuitions);
        nursing = findViewById(R.id.nursing);

        caring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Main2Activity.this,Caring.class);
                startActivity(i);
            }
        });

        picking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,Picking.class);
                startActivity(i);
            }
        });

        tuitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,Tuitions.class);
                startActivity(i);
            }
        });
        nursing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,Nursing.class);
                startActivity(i);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_signout) {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(Main2Activity.this,Login.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.menu_changepassword) {
            Intent i = new Intent(Main2Activity.this,Changepassword.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
