package com.sam.notifications_r.notification

import android.Manifest
import android.annotation.SuppressLint
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
import android.app.Notification.ProgressStyle.Segment


object NotificationUtility {
    private const val NOTIFICATION_CHANNEL_ID = "12345"
    const val MARK_AS_READ_ACTION = "MARK_AS_READ_ACTION"
    const val PLAY_SOUND_ACTION = "PLAY_SOUND_ACTION"
    private const val PROGRESS_NOTIFICATION_ID = 99
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

    @SuppressLint("LaunchActivityFromNotification")
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun createStickyNotification(context: Context) {
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = PLAY_SOUND_ACTION
        }

        val pendingIntent = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Sticky Notification Title")
            .setContentText("This is a sticky notification text")
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()

        NotificationManagerCompat.from(context).notify(1, notification)

    }


    fun createProgressNotification(context: Context) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val segment = Segment(100)
        val progressStyle = Notification.ProgressStyle()
            .setProgressSegments(listOf(segment))
            .setStyledByProgress(true)
        val builder = Notification.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setSubText("Downloading Notification Title")
            .setContentTitle("Downloading text")
            .setContentText("Downloading document text")
            .setStyle(progressStyle)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
        Thread {
            for (i in 0..100){
//                val progress = (1 / 100f)
                progressStyle.setProgress(i)

                notificationManager.notify(PROGRESS_NOTIFICATION_ID, builder.build())

                Thread.sleep(300)
            }

            builder.setContentTitle("Download finished")
                .setOngoing(false)
            notificationManager.notify(PROGRESS_NOTIFICATION_ID, builder.build())
        }.start()
    }
}