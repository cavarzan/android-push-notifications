package io.esparta.notifications.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import io.esparta.notifications.R
import io.esparta.notifications.entity.Notification
import io.esparta.notifications.service.PushReceiverIntentService
import io.esparta.notifications.utils.Extras
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)

        dont_abort_broadcast_button.setOnClickListener { startActivity(Intent(this, ReceivePushAndDontAbortActivity::class.java)) }

        abort_broadcast_button.setOnClickListener { startActivity(Intent(this, ReceivePushAndAbortActivity::class.java)) }

        emit_and_close_button.setOnClickListener { dispatchNotificationAndFinish() }

    }

    private fun dispatchNotificationAndFinish() {

        Handler().postDelayed({
            val intent = Intent(this, PushReceiverIntentService::class.java)
            val notification = Notification("Default Notification", "This notification was received in default Receiver")
            intent.putExtra(Extras.NOTIFICATION, notification)
            startService(intent)
        }, 2000)

        finish()
    }

}
