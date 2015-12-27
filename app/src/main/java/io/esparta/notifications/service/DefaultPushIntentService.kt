package io.esparta.notifications.service

import android.app.IntentService
import android.app.NotificationManager
import android.content.Intent
import android.content.Context
import android.support.v4.app.NotificationCompat
import io.esparta.notifications.R
import io.esparta.notifications.entity.Notification
import io.esparta.notifications.utils.Extras
import java.util.concurrent.atomic.AtomicInteger

class DefaultPushIntentService : IntentService("DefaultPushIntentService") {

    companion object {
        val uuid: AtomicInteger = AtomicInteger(1)
    }

    override fun onHandleIntent(intent: Intent) {

        val notification = intent.getParcelableExtra<Notification?>(Extras.NOTIFICATION)

        notification?.let {
            val mBuilder = NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(notification.title)
                    .setContentText(notification.message)
                    .setSubText(getString(R.string.app_name))
                    .setAutoCancel(true);

            val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
            mNotificationManager.notify("default-push", uuid.andIncrement, mBuilder.build());
        }

    }

}
