package com.example.android.background.utilities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    public static final int WATER_REMINDER_PENDING_INTENT_ID = 1;
    public static final int WATER_REMINDER_NOTIFICATION_ID = 1;

    // COMPLETED (7) Create a method called remindUserBecauseCharging which takes a Context.
    // This method will create a notification for charging. It might be helpful
    // to take a look at this guide to see an example of what the code in this method will look like:
    // https://developer.android.com/training/notify-user/build-notification.html
    public static void remindUserBecauseCharging(Context context) {
        // COMPLETED (8) In the remindUser method use NotificationCompat.Builder to create a notification
        // that:
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
        // - has a color of R.colorPrimary - use ContextCompat.getColor to get a compatible color
        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
        // - has ic_drink_notification as the small icon
        .setSmallIcon(R.drawable.ic_drink_notification)
        // - uses icon returned by the largeIcon helper method as the large icon
        .setLargeIcon(largeIcon(context))
        // - sets the title to the charging_reminder_notification_title String resource
        .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
        // - sets the text to the charging_reminder_notification_body String resource
        .setContentText(context.getString(R.string.charging_reminder_notification_body))
        // - sets the style to NotificationCompat.BigTextStyle().bigText(text)
        .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.charging_reminder_notification_body)))
        // - sets the notification defaults to vibrate
        .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
        // - uses the content intent returned by the contentIntent helper method for the contentIntent
        .setContentIntent(contentIntent(context))
        // - automatically cancels the notification when the notification is clicked
        .setAutoCancel(true);
        // COMPLETED (9) If the build version is greater than JELLY_BEAN, set the notification's priority
        // to PRIORITY_HIGH.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        // COMPLETED (11) Get a NotificationManager, using context.getSystemService(Context.NOTIFICATION_SERVICE);
        //NotificationManager notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // COMPLETED (12) Trigger the notification by calling notify on the NotificationManager.
        // Pass in a unique ID of your choosing for the notification and notificationBuilder.build()
        notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, builder.build());
    }


    // COMPLETED (1) Create a helper method called contentIntent with a single parameter for a Context. It
    // should return a PendingIntent. This method will create the pending intent which will trigger when
    // the notification is pressed. This pending intent should open up the MainActivity.
    private static PendingIntent contentIntent(Context context) {
        // COMPLETED (2) Create an intent that opens up the MainActivity
        Intent launchIntent = new Intent(context, MainActivity.class);

        // COMPLETED (3) Create a PendingIntent using getActivity that:
        return PendingIntent.getActivity(
            // - Take the context passed in as a parameter
                context,
            // - Takes an unique integer ID for the pending intent (you can create a constant for
            //   this integer above
                WATER_REMINDER_PENDING_INTENT_ID,
            // - Takes the intent to open the MainActivity you just created; this is what is triggered
            //   when the notification is triggered
                launchIntent,
            // - Has the flag FLAG_UPDATE_CURRENT, so that if the intent is created again, keep the
            // intent but update the data
                PendingIntent.FLAG_UPDATE_CURRENT);
    }


    // COMPLETED (4) Create a helper method called largeIcon which takes in a Context as a parameter and
    // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
    private static Bitmap largeIcon(Context context) {
        // COMPLETED (5) Get a Resources object from the context.
        Resources resources = context.getResources();
        // COMPLETED (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        // resources object and R.drawable.ic_local_drink_black_24px
        return BitmapFactory.decodeResource(resources, R.drawable.ic_local_drink_black_24px);
    }
}
