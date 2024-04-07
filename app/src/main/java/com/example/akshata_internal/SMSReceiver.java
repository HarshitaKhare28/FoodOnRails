package com.example.akshata_internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = null;
        String str = "SMS from ";
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (i == 0) { // get the sender address/phone number
                    str += messages[i].getOriginatingAddress();
                    str += ":";
                }
            }
            for (int i = 0; i < messages.length; i++)
                str += messages[i].getMessageBody().toString();
        }
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("SMS_RECEIVED_ACTION");
        broadcastIntent.putExtra("sms", str);
        context.sendBroadcast(broadcastIntent);
    }
}
