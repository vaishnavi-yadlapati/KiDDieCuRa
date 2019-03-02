package com.example.supraja_pc.kiddiecura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Nursing extends AppCompatActivity {
    Spinner medication;
    private Object v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursing);

        medication = findViewById(R.id.medication);

        List<String> categories = new ArrayList<String>();
        //categories.add("Gender");
        categories.add("Pediasure");
        categories.add("Inhalex (cold)");
        categories.add("Ambrodil Syrup (cough)");
        categories.add("Dolo 250 (fever)");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medication.setAdapter(dataAdapter1);



    }
}
