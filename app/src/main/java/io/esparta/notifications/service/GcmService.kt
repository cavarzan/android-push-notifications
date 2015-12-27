package io.esparta.notifications.service

import android.content.Intent
import android.os.Bundle
import com.google.android.gms.gcm.GcmListenerService

/**
 * Created by deividi on 26/12/15.
 */
class GcmService : GcmListenerService() {

    override fun onMessageReceived(from: String?, data: Bundle?) {
        val intent = Intent(this, PushReceiverIntentService::class.java)
        intent.putExtras(data)
        startService(intent)
    }

}