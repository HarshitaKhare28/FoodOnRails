package com.example.akshata_internal;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderConfirmed extends AppCompatActivity {


    TextView itemTextView,nameTextView,phoneTextView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.order_confirmed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        itemTextView = (TextView) findViewById(R.id.itemTextView);
        //nameTextView = (TextView) findViewById(R.id.nameTextView);
        //phoneTextView = (TextView) findViewById(R.id.phoneTextView);

        Intent receive = getIntent();
        String item = receive.getStringExtra("item");
        if (item != null) {
            item = "Item: " + item;
            itemTextView.setText(item);
        }

//        String[] nameAndPhone = my_db.getNameAndPhoneByItem(item);
//        Log.d("DEBUG", "Item: " + item);
//        if (nameAndPhone != null) {
//            Log.d("DEBUG", "Name: " + nameAndPhone[0] + ", Phone: " + nameAndPhone[1]);
//            nameTextView.setText("Name: " + nameAndPhone[0]);
//            phoneTextView.setText("Phone: " + nameAndPhone[1]);
//        } else {
//            Log.d("DEBUG", "No matching record found for item: " + item);
//        }
    }
}