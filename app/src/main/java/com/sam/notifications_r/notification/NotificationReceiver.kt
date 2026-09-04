package com.sam.notifications_r.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras
        val title = extras?.getString("title")
        val text = extras?.getString("text")
        Log.d("NotificationReceiver", "Title: $title")
        Log.d("NotificationReceiver", "Text: $text")

        if(intent.action == NotificationUtility.PLAY_SOUND_ACTION){
            val toneG = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200)

            Log.d("NotificationReceiver", "Sticky notification clicked - Sound played!")
        }
    }
}


