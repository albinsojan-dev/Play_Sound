package com.albin.playSound.clock

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class PlaySound {
    // Play the sound in the background when the time matches
    fun playSound(context: Context, soundResource: Int) {
        // Retrieve shared preferences
        val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val playSound = sharedPreferences.getBoolean("play_Clock", false)
        if (playSound) {
            val manager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            manager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0)
//             val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val player: MediaPlayer = MediaPlayer.create(context, soundResource)
            if(manager.ringerMode != AudioManager.RINGER_MODE_SILENT)
                player.start()
        }
    }
}










