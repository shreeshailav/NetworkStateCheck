package com.shreeshail.networkstatecheck;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.shreeshail.rxnetworkstate.ConnectionManager;
import com.shreeshail.rxnetworkstate.ConnectionTracer;

public class MainActivity extends AppCompatActivity implements ConnectionTracer {

    TextView networkStatus;

    ConnectionManager connectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkStatus = findViewById(R.id.networkStatus);

        connectionManager = new ConnectionManager.Builder()
                .setContext(this)
                .setStatusView(this)
                .build();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
     }

    @Override
    public void connectionState(int isConnected) {
        if(isConnected == 1){

            ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            int connectonType = activeNetwork.getType();
            String connType = "";
            if(connectonType == ConnectivityManager.TYPE_WIFI)
                connType = "WIFI";
            else if (connectonType == ConnectivityManager.TYPE_MOBILE)
                connType = "MOBILE";
            Log.i("RX-Internet : ","Online : "+ connType);
            networkStatus.setText("Online" + connType);
            networkStatus.setTextColor(Color.BLUE);
        }
        else {
            Log.i("RX-Internet : ", "Offline");
            networkStatus.setText("Offline");
            networkStatus.setTextColor(Color.RED);
        }
    }
}