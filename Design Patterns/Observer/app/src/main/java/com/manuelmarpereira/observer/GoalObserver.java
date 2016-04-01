package com.manuelmarpereira.observer;

import android.app.NotificationManager;
import android.content.Context;

import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by manuel on 3/15/16.
 */
public class GoalObserver extends Observer {

    Context context;

    public GoalObserver(TeamSubject subject, Context context){
        this.subject = subject;
        this.subject.attach(this);
        this.context= context;
    }


    @Override
    public void update() {

        //Create new notification
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_ball)
                .setContentTitle("GOOOOOAL")//title
                .setContentText(subject.getName()+" Goal"); //content text

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build()); //notify

        Toast.makeText(context, subject.getName()+" Goal", Toast.LENGTH_SHORT).show();

    }


}
