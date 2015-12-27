package io.esparta.notifications.service

import android.content.Context
import android.content.Intent
import android.support.v4.content.WakefulBroadcastReceiver

class DefaultPushReceiver : WakefulBroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val intent = Intent(context, DefaultPushIntentService::class.java)
        intent.putExtras(getResultExtras(true))
        context.startService(intent)
    }
}
