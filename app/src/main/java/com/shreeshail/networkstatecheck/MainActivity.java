package com.shreeshail.networkstatecheck;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
        if(isConnected==1){
            Log.i("RX-Internet : ","Online");
            networkStatus.setText("Online");
            networkStatus.setTextColor(Color.BLUE);
        }
        else {
            Log.i("RX-Internet : ", "Offline");
            networkStatus.setText("Offline");
            networkStatus.setTextColor(Color.RED);
        }
    }
}