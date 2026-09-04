package com.sam.notifications_r

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.sam.notifications_r.notification.NotificationReceiver
import com.sam.notifications_r.notification.NotificationScreen
import com.sam.notifications_r.notification.NotificationUtility
import com.sam.notifications_r.ui.theme.NotificationsRTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var notificationReceiver: NotificationReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationScreen()
        }

        notificationReceiver = NotificationReceiver()
        ContextCompat.registerReceiver(
            this,
            notificationReceiver,
            IntentFilter(NotificationUtility.MARK_AS_READ_ACTION),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        NotificationUtility.createNotificationChannel(this)
    }

    override fun onDestroy() {
        unregisterReceiver(notificationReceiver)
        super.onDestroy()
    }
}

