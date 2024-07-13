package com.albin.playSound.clock

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class PlaySound {
    fun play(result: Int, soundResources: List<Int>, context: Context) {
        val soundResource = soundResources.getOrNull(result) ?: return
        val manager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        manager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0)
        val player = MediaPlayer.create(context, soundResource) ?: return
        player.setOnCompletionListener {
            player.release()
        }
        if (manager.ringerMode != AudioManager.RINGER_MODE_SILENT) {
            player.start()
        }
    }
}









