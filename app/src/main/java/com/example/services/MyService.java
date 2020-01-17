package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {

    private static final String TAG = "MyService_TAG";

    private boolean flag = false;

    private MyBinder binder = new MyBinder();
    private Random generator = new Random();

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MyService is created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Service is started");
        for(int i=0; i<1000; i++) {
            Log.d(TAG, "onStartCommand: working " + i);
        }
//        stopSelf();
//        flag = true;
//        while(flag) {
//
//            try {
//                Thread.sleep(1000);
//                Log.d(TAG, "onStartCommand: working...");
//            } catch (InterruptedException ie) {
//                ie.printStackTrace();
//                flag = false;
//            }
//
//        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: service stopped and destroyed");
    }

    public int getRandomNumber() {
        return generator.nextInt(100);
    }

    public class MyBinder extends Binder {

        MyService getService() {
            return MyService.this;
        }
    }
}
