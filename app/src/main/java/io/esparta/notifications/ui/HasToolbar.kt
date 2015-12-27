package io.esparta.notifications.ui

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import io.esparta.notifications.R

/**
 * Created by deividi on 26/12/15.
 */
interface HasToolbar {

    val toolbar: Toolbar

    fun configureToolbar(activity : AppCompatActivity) : Unit {
        toolbar.title = activity.getString(R.string.app_name)
        activity.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { activity.finish() }
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
    }

}