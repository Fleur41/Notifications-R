package com.sam.notifications_r.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intent = intent.extras
        val title = intent?.getString("title")
        val text = intent?.getString("text")
        Log.d("NotificationReceiver", "Title: $title")
        Log.d("NotificationReceiver", "Text: $text")
    }
}


