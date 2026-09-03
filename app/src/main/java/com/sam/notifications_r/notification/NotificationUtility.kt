package com.sam.notifications_r.notification

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sam.notifications_r.R
import kotlin.jvm.java


object NotificationUtility {
    private const val NOTIFICATION_CHANNEL_ID = "12345"
    const val MARK_AS_READ_ACTION = "MARK_AS_READ_ACTION"
    fun createNotificationChannel(context: Context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID ,
                "General Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "This is a channel for showing general notifications"
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun createSimpleNotification(context: Context){
        val notification: Notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Basic Notification Title")
            .setContentText("This is a basic notification text")
            .build()

        NotificationManagerCompat.from(context).notify(1, notification)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun createClickableNotification(context: Context) {
        val notificationTitle = "Clickable Notification Title"
        val notificationText = "This is a clickable notification text"
        val intent = Intent(context, NotificationDetailActivity::class.java).apply {
            putExtra("title", notificationTitle)
            putExtra("text", notificationText)
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(1, notification)


    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun createActionableNotification(context: Context) {
        val notificationTitle = "Actionable Notification Title"
        val notificationText = "This is a actionable notification text"
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = MARK_AS_READ_ACTION
            putExtra("title", notificationTitle)
            putExtra("text", notificationText)
        }

        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .addAction(R.drawable.ic_launcher_foreground, "Mark as read", pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(1, notification)


    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun createStickyNotification(context: Context) {
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Sticky Notification Title")
            .setContentText("This is a sticky notification text")
//            .setStyle(Notification.MediaStyle())
            .setOngoing(true)
            .build()

        NotificationManagerCompat.from(context).notify(1, notification)

    }
}