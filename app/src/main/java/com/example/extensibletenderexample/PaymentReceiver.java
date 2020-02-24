package com.example.extensibletenderexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.clover.sdk.v1.Intents;


public class PaymentReceiver extends BroadcastReceiver {
    private Context mContext;
    private String TAG = "PaymentReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;

        String orderId = intent.getStringExtra(Intents.EXTRA_CLOVER_ORDER_ID);
        String paymentId = intent.getStringExtra(Intents.EXTRA_CLOVER_PAYMENT_ID);
        String tenderKey = intent.getStringExtra(Intents.EXTRA_CLOVER_TENDER_LABEL_KEY);
        long amount = intent.getLongExtra("com.clover.intent.extra.AMOUNT", 0L);
        Log.i(TAG, "Payment processed! Order: " + orderId + " Payment ID: " + paymentId + " Amount: " + amount);

        Log.i(TAG, "Checking if " + orderId + " matches last tender click order..");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String lastOrderID = prefs.getString("last_order_id", null);

        Log.i(TAG, "Payment order id: " + orderId);
        Log.i(TAG, "Cached order id: " + lastOrderID);
        if(orderId.equals(lastOrderID)){
            Log.i(TAG, "Order ids match, no issues here");
        }
        else{
            Log.i(TAG, "Order ids do NOT match, did you invoke the issue by paying with credit card?");
        }
    }
}