package com.albin.playsoundtheinonehourintervals

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class SoundPlayer(private val context: Context) {
    private val soundResources = listOf(
        R.raw.zero, R.raw.one, R.raw.two, R.raw.three, R.raw.four,
        R.raw.five, R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine,
        R.raw.ten, R.raw.eleven, R.raw.twelve, R.raw.thirteen, R.raw.fourteen,
        R.raw.fifteen, R.raw.sixteen, R.raw.seventeen, R.raw.eighteen,
        R.raw.nineteen, R.raw.twenty, R.raw.twenty_one, R.raw.twenty_two, R.raw.twenty_three
    )

    private fun playSound(result: Int) {
        val soundResource = soundResources[result]
        val mediaPlayer = MediaPlayer.create(context, soundResource)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            it.release()
        }
    }

    fun startTimer () {
        val now = MutableLiveData<LocalTime>()
        var result = -1

        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                val currentTime = LocalTime.now()
                now.postValue(currentTime)

                if (result != currentTime.hour) {
                    result = currentTime.hour
                    playSound(result)
                }

                delay(1000)
            }
        }
    }
}


