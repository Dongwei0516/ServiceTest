package com.example.dongwei.servicetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by dongwei on 2016/11/1.
 */

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress(){
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder =new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setTicker("Notice");
        builder.setContentTitle("Title");
        builder.setContentText("Content");
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_ALL);
//        builder.setAutoCancel(true);
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,0);
        builder.setContentIntent(pendingIntent);
        Notification notification =builder.build();
        notificationManager.notify(0,notification);

//        Notification notification = new Notification(R.drawable.ic_launcher, "Notification comes", System.currentTimeMillis());
//        Intent notificationIntent  = new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent , 0);
//        notification.setLatestEventInfo(this, "This is title", "This is content", pendingIntent);
//        startForeground(1,notification);
        Log.d("MyService", "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent , flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }
}
