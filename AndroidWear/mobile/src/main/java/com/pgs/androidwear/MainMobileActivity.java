package com.pgs.androidwear;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class MainMobileActivity extends Activity {
    final static String GROUP_KEY_EMAILS = "group_key_emails";


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);



        Button voiceBtn = (Button) findViewById(R.id.wearButton);

        voiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stackingNotifications(getApplicationContext());

            }
        });
    }

    private void stackingNotifications(Context context){
        Bitmap background1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.bg1);

        Bitmap background2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.bg2);

        int notificationId1 = 001;
        int notificationId2 = 002;
        int notificationId3 = 003;

        // Build the notification, setting the group appropriately
        Notification notif = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from " + "Wojciech")
                .setContentText("temat 1")
                .setSmallIcon(R.drawable.ic_launcher)
                .setGroup(GROUP_KEY_EMAILS)
                .build();

        // Issue the notification
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId1, notif);

        NotificationCompat.WearableExtender wearableExtender1 =
                new NotificationCompat.WearableExtender()
                        .setBackground(background2);

        Notification notif2 = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from " + "Adam")
                .setContentText("temat 2")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(background1)
                .extend(wearableExtender1)
                .setGroup(GROUP_KEY_EMAILS)
                .build();

        notificationManager.notify(notificationId2, notif2);

        //**************
        /*Notification summaryNotification = new NotificationCompat.Builder(this)
                .setContentTitle("2 new messages")
                .setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Alex Faaborg   Check this out")
                        .addLine("Jeff Chang   Launch Party")
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("johndoe@gmail.com"))
                .setGroup(GROUP_KEY_EMAILS)
                .setGroupSummary(true)
                .build();

        notificationManager.notify(notificationId3, summaryNotification);*/

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setBackground(background1);

        // Create an InboxStyle notification
        Notification summaryNotificationWithBackground =
                new NotificationCompat.Builder(this)
                        .setContentTitle("2 new messages")
                        .extend(wearableExtender)
                        .setGroup(GROUP_KEY_EMAILS)
                        .setGroupSummary(true)
                        .build();
        notificationManager.notify(notificationId3, summaryNotificationWithBackground);
    }

    private void notificationPages() {

        int notificationId = 001;

        // Build intent for notification content
        Intent viewIntent = new Intent(this, ViewEventActivity.class);
        //viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Create builder for the main notification
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Page 1")
                        .setContentText("Short message")
                        .setContentIntent(viewPendingIntent);

        // Create a big text style for the second page
        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
        secondPageStyle.setBigContentTitle("Page 2")
                .bigText("A lot of text...");

        // Create second page notification
        Notification secondPageNotification =
                new NotificationCompat.Builder(this)
                        .setStyle(secondPageStyle)
                        .build();

        // Extend the notification builder with the second page
        Notification notification = notificationBuilder
                .extend(new NotificationCompat.WearableExtender()
                        .addPage(secondPageNotification))
                .build();

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notification);
    }

    private void voiceNotification() {
       /* Intent replyIntent = new Intent(this, ViewEventActivity.class);

        PendingIntent replyPendingIntent = PendingIntent.getActivity(this, 0, replyIntent, 0);

        NotificationCompat.Builder replyNotificationBuilder = new NotificationCompat.Builder(this);
        replyNotificationBuilder.setSmallIcon(android.R.drawable.ic_btn_speak_now);
        replyNotificationBuilder.setContentTitle("Messaggio");
        replyNotificationBuilder.setContentText("Testo del messaggio");
        replyNotificationBuilder.setContentIntent(replyPendingIntent);
        replyNotificationBuilder.setNumber(++numMessages);
        replyNotificationBuilder.setAutoCancel(true);
        replyNotificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        replyNotificationBuilder.setVibrate(new long[]{1000, 1000});
        replyNotificationBuilder.setTicker("Hai una nuova notifica!");

        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, replyNotificationBuilder.build());

        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY).setLabel(getResources().getString(R.string.replyLabel)).build();
        Notification replyNotification = new WearableNotifications.Builder(replyNotificationBuilder).addRemoteInputForContentIntent(remoteInput).build();
        NotificationManagerCompat.from(this).notify(0, replyNotification);*/
    }

    private void normalNotification(){
        int notificationId = 001;

        Intent viewIntent = new Intent(getApplicationContext(), ViewEventActivity.class);
        //viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, viewIntent, 0);



        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(MainMobileActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("title")
                        .setContentText("HEY HI HELLO")
                        .setContentIntent(viewPendingIntent);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainMobileActivity.this);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void notificationWithButton(){
        Intent intent = new Intent(MainMobileActivity.this, ViewEventActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainMobileActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                R.drawable.ic_launcher,
                getString(R.string.wearTitle),
                pendingIntent).build();

        Notification notification = new NotificationCompat.Builder(MainMobileActivity.this)
                .setContentText("title1")
                .setContentText("content1")
                .setSmallIcon(R.drawable.ic_launcher)
                .extend(new NotificationCompat.WearableExtender().addAction(action))
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainMobileActivity.this);
        notificationManagerCompat.notify(001, notification);
    }

}
