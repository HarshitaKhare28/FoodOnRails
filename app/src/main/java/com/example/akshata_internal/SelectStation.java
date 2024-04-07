package com.example.akshata_internal;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectStation extends AppCompatActivity {

    Spinner sp1;
    Spinner pick;
    Spinner plat;
    String[] stations = {"New Delhi Railway Station (NDLS)", "Chhatrapati Shivaji Maharaj Terminus (CSMT)", "Howrah Junction (HWH)", "Chennai Central (MAS)", "Bangalore City Railway Station (SBC)"};
    Integer[] platforms = {1,2,3,4,5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.select_station);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sp1 = findViewById(R.id.sp1);
        pick = findViewById(R.id.pickstation);
        plat = findViewById(R.id.pickplat);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stations);
        ArrayAdapter<Integer> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, platforms);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        pick.setAdapter(adapter);

        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plat.setAdapter(adp);
        Button submitButton = findViewById(R.id.submitloc);

        plat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer i = (Integer) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentStation = sp1.getSelectedItem().toString();
                String pickupStation = pick.getSelectedItem().toString();
                Integer platformNumber = (Integer) plat.getSelectedItem();

                Toast.makeText(getApplicationContext(), "Current Station: " + currentStation + ", Pickup Station: " + pickupStation , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SelectStation.this, ChooseFood.class);
                intent.putExtra("pickupStation", pickupStation);
                intent.putExtra("platform", platformNumber);
                startActivity(intent);
            }
        });
    }
}
