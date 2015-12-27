package io.esparta.notifications.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import io.esparta.notifications.R
import io.esparta.notifications.entity.Notification
import io.esparta.notifications.service.PushReceiverIntentService
import io.esparta.notifications.utils.Extras
import io.esparta.notifications.utils.registerPushReceiver
import kotlinx.android.synthetic.main.content_details.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class ReceivePushAndDontAbortActivity : AppCompatActivity(), HasToolbar {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_push_and_not_abort)
        configureToolbar(this)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            dispatchNotification()
        }, 1000)
    }

    private fun dispatchNotification() {
        val intent = Intent(this, PushReceiverIntentService::class.java)
        val notification = Notification("ReceivePushAndDontAbortActivity Notification", "This notification was received in ReceivePushAndDontAbortActivity")
        intent.putExtra(Extras.NOTIFICATION, notification)
        startService(intent)
    }

    private fun updateView(notification : Notification) {
        notification_title.text = notification.title
        notification_message.text = notification.message
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            toast(R.string.message_received)
            val notification = intent.getParcelableExtra<Notification?>(Extras.NOTIFICATION)
            notification?.let {
                updateView(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        registerPushReceiver(receiver)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}
