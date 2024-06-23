package com.albin.playsoundtheinonehourintervals.CheckWifiisConnectedOrNot

import android.content.Context
import android.media.MediaPlayer

class PlaySound {
    // Function to create and play a sound
     fun playSound(context: Context, soundResource: Int) {
        val mediaPlayer = MediaPlayer.create(context, soundResource)
        mediaPlayer.start()
    }
}
