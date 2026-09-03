package com.sam.notifications_r.notification

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.core.view.WindowInsetsCompat
import com.sam.notifications_r.R

class NotificationDetailActivity : AppCompatActivity() {
    lateinit var notificationTitle: TextView
    lateinit var notificationText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_detail)

        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")

        notificationTitle = findViewById(R.id.notificationTitle)
        notificationText = findViewById(R.id.notificationText)

        notificationTitle.text = title
        notificationText.text = text
    
    }
}