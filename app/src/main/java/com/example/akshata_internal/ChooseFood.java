package com.example.akshata_internal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChooseFood extends AppCompatActivity {

    TextView input_city, input_pform;
    ImageButton burger, pizza, dosa, momos, noodles, pasta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.choose_food);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input_city = (TextView) findViewById(R.id.input_city);

        Intent receiverIntent = getIntent();
        String pickUp = receiverIntent.getStringExtra("pickupStation");
//        String platform = receiverIntent.getStringExtra("platform");
        input_city.setText(pickUp);
//        input_pform.setText(platform);

        burger = (ImageButton) findViewById(R.id.burger);
        pizza = (ImageButton) findViewById(R.id.pizza);
        momos = (ImageButton) findViewById(R.id.momos);
        dosa = (ImageButton) findViewById(R.id.dosa);
        pasta = (ImageButton) findViewById(R.id.pasta);
        noodles = (ImageButton) findViewById(R.id.noodles);

        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFood.this, OtpSMSActivity.class);
                intent.putExtra("cost","150");
                startActivity(intent);
            }
        });

        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFood.this, OtpSMSActivity.class);
                intent.putExtra("cost","300");
                startActivity(intent);
            }
        });

        momos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFood.this, OtpSMSActivity.class);
                intent.putExtra("cost","100");
                startActivity(intent);
            }
        });

        dosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFood.this, OtpSMSActivity.class);
                intent.putExtra("cost","70");
                startActivity(intent);
            }
        });

        noodles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFood.this, OtpSMSActivity.class);
                intent.putExtra("cost","170");
                startActivity(intent);
            }
        });

        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFood.this, OtpSMSActivity.class);
                intent.putExtra("cost","300");
                intent.putExtra("phoneNo", (String) null);
                startActivity(intent);
            }
        });
    }

}