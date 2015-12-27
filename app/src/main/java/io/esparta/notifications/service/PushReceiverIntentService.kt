package io.esparta.notifications.service

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import io.esparta.notifications.entity.Notification
import io.esparta.notifications.utils.Constants
import io.esparta.notifications.utils.Extras


public class PushReceiverIntentService : IntentService("PushReceiverIntentService") {

    val callback: Handler.Callback = Handler.Callback({
        msg ->
        throw IllegalArgumentException("PUSH_RECEIVED NOT HANDLED!")
    })

    private fun sendNotification(notification: Notification, extras: Bundle = Bundle()) {

        extras.putParcelable(Extras.NOTIFICATION, notification);

        val broadcast = Intent();
        broadcast.putExtras(extras);
        broadcast.setAction(Constants.BROADCAST_NOTIFICATION);

        sendOrderedBroadcast(broadcast, null, null, Handler(callback), Activity.RESULT_OK, null, extras);
    }

    override fun onHandleIntent(intent: Intent) {
        val extras = intent.extras;

        val notification: Notification? = extras.getParcelable(Extras.NOTIFICATION)

        if (notification != null) {
            sendNotification(notification)
        } else {

            val message = extras.getString("message");
            val url = extras.getString("url");

            sendNotification(Notification(url, message), extras);

        }
    }

}



