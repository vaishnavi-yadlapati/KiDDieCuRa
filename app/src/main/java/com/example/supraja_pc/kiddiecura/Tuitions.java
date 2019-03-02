package com.example.supraja_pc.kiddiecura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Tuitions extends AppCompatActivity {
    Spinner subjects,level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuitions);

        subjects = findViewById(R.id.subjects);
        level = findViewById(R.id.level);

        //spinner for subjects
        List<String> categories = new ArrayList<String>();
        categories.add("Maths");
        categories.add("Physics");
        categories.add("Biology");
        categories.add("Chemistry");
        categories.add("English Grammar");
        categories.add("Hindi");
        categories.add("Telugu");
        categories.add("Vedic Maths");
        categories.add("Abacus");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjects.setAdapter(dataAdapter);

        //spinner for level

        List<String> categories1 = new ArrayList<String>();
        //categories.add("Gender");
        categories1.add("Basic");
        categories1.add("Intermediate");
        categories1.add("Advanced");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(dataAdapter1);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        // Showing selected spinner item
        // text = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
