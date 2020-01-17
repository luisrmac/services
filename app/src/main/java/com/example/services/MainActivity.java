package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.example.services.MyService.MyBinder;

public class MainActivity extends AppCompatActivity {

    private Intent myServiceIntent;
    private MyService myService;

    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMyServiceIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // bind to the service
        Intent boundIntent = new Intent(this, MyService.class);
        bindService(boundIntent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // unbind to the service
        unbindService(connection);
        isConnected = false;
    }

    public void initMyServiceIntent() {
        myServiceIntent = new Intent(this, MyService.class);
    }

    public void startMyService(View view) {
        startService(myServiceIntent);
    }

    public void stopMyService(View view) {
        stopService(myServiceIntent);
    }

    public void startIntentService(View view) {
        for(int i = 0; i<10; i++) {
            if(i==1) {
                MyIntentService.startActionBaz(this,"Hello", "Long work");
            }
            MyIntentService.startActionFoo(this, "Hello", "Miguel " + i);
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MyBinder myBinder = (MyBinder) binder;
            myService = myBinder.getService();
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };

    public void placeRequestToService(View view) {
        String randNumber = String.valueOf(myService.getRandomNumber());
        Toast.makeText(myService, randNumber, Toast.LENGTH_SHORT).show();
    }
}
