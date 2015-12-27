package io.esparta.notifications.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter

/**
 * Created by deividi on 26/12/15.
 */
public fun Context.registerPushReceiver(receiver: BroadcastReceiver) {
    val filter = IntentFilter(Constants.BROADCAST_NOTIFICATION)
    filter.priority = 1
    registerReceiver(receiver, filter)
}
