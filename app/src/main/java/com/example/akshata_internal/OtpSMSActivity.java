package com.example.akshata_internal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
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
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class OtpSMSActivity extends AppCompatActivity {

    EditText phone, enter_otp;
    TextView amount;
    Button otpButton, submit;
    private static final String CHANNEL_ID = "order_channel";

    private boolean isOtpSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.otp_sms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        phone = (EditText) findViewById(R.id.phoneNumber);
        enter_otp = (EditText) findViewById(R.id.enter_otp);
        amount = (TextView) findViewById(R.id.amount);
        otpButton = (Button) findViewById(R.id.otpButton);
        submit = (Button) findViewById(R.id.submit);

        Intent receive = getIntent();
        String cost = "Amount: " + (receive.getStringExtra("cost"));
        String phoneNumber = receive.getStringExtra("phoneNo");
        String item = receive.getStringExtra("item");
        phone.setText(phoneNumber);
        amount.setText(cost);

        if (ContextCompat.checkSelfPermission(OtpSMSActivity.this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            int REQUEST_SEND_SMS = 123;
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
        } else {
            otpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = phone.getText().toString();
                    String message = "OTP for FoodOnRails: 7516";
                    SmsManager smsManager = SmsManager.getDefault();
                    Intent intent = new Intent(getApplicationContext(), OtpSMSActivity.class);
                    intent.putExtra("cost", getIntent().getStringExtra("cost"));
                    intent.putExtra("phoneNo",phoneNumber);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
                    smsManager.sendTextMessage(phoneNumber, null, message, pendingIntent, null);
                    isOtpSent = true;
                    otpButton.setEnabled(false);
                }
            });
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enter_otp.getText().toString().trim().equalsIgnoreCase("7516")) {
                    Toast.makeText(getApplicationContext(), "Correct OTP", Toast.LENGTH_LONG).show();
                    sendOrderConfirmedNotification();
                    openNotificationCheckerActivity(item);
                }
            }
        });
    }

    private void sendOrderConfirmedNotification() {
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Order Channel";
            String description = "Channel for order notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void openNotificationCheckerActivity(String item) {
        Intent intent = new Intent(this, OrderConfirmed.class);
        intent.putExtra("item",item);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle("Order Confirmed")
                .setContentText("Your order has been confirmed!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(new Random().nextInt(), builder.build());
    }

}