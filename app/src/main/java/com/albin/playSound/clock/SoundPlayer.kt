package com.albin.playSound.clock

import android.content.Context
import android.content.ContextWrapper
import android.database.ContentObserver
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import android.util.Log
import com.albin.playSound.R

@RequiresApi(Build.VERSION_CODES.O)
class SoundPlayer(context: Context) : ContextWrapper(context) {

    private val handler = Handler(Looper.getMainLooper())

    @RequiresApi(Build.VERSION_CODES.O)
    private val timeFormatObserver = TimeFormatObserver(this, handler) {
        Log.d("SoundPlayer", "Time format changed")
        startTimer()
    }

    private val twentyFourHourSoundResources = listOf(
        R.raw.zero, R.raw.one, R.raw.two, R.raw.three, R.raw.four,
        R.raw.five, R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine,
        R.raw.ten, R.raw.eleven, R.raw.twelve, R.raw.thirteen, R.raw.fourteen,
        R.raw.fifteen, R.raw.sixteen, R.raw.seventeen, R.raw.eighteen,
        R.raw.nineteen, R.raw.twenty, R.raw.twenty_one, R.raw.twenty_two, R.raw.twenty_three
    )

    private val twelveHourSoundResources = listOf(
        R.raw.twelve, R.raw.one, R.raw.two, R.raw.three, R.raw.four,
        R.raw.five, R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine,
        R.raw.ten, R.raw.eleven, R.raw.twelve
    )

    private var mediaPlayer: MediaPlayer? = null
    private var timerJob: Job? = null

    // some error when volume button clicked the sound play{
    init {
        contentResolver.registerContentObserver(
            Settings.System.CONTENT_URI,
            true,
            timeFormatObserver
        )
        Log.d("SoundPlayer", "ContentObserver registered")
    }
    // }

    private fun playSound(result: Int, soundResources: List<Int>) {
        val sharedPreferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val playSound = sharedPreferences.getBoolean("play_Clock", false)

        if (playSound) {
            val soundResource = soundResources[result]
            mediaPlayer?.release() // Release any existing MediaPlayer instance
            mediaPlayer = MediaPlayer.create(this, soundResource).apply {
                start()
                setOnCompletionListener {
                    it.release()
                    Log.d("SoundPlayer", "MediaPlayer released after completion")
                }
            }
            Log.d("SoundPlayer", "Playing sound for hour: $result")
        }
    }

    fun release() {
        stopTimer()
        contentResolver.unregisterContentObserver(timeFormatObserver)
        mediaPlayer?.release()
        Log.d("SoundPlayer", "Resources released")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startTimer() {
        val is24HourFormat = android.text.format.DateFormat.is24HourFormat(this)
        if (is24HourFormat) {
            Log.d("SoundPlayer", "Starting 24-hour timer")
            start24HourTimer()
        } else {
            Log.d("SoundPlayer", "Starting 12-hour timer")
            start12HourTimer()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun start24HourTimer() {
        stopTimer() // Stop any existing timer before starting a new one
        var previousHour = -1
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                val currentTime = LocalTime.now()
                val currentHour = currentTime.hour
                if (previousHour != currentHour) {
                    previousHour = currentHour
                    playSound(currentHour, twentyFourHourSoundResources)
                    Log.d("SoundPlayer", "Current time: ${currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))}")
                }
                delay(60_000) // Check every minute
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun start12HourTimer() {
        stopTimer() // Stop any existing timer before starting a new one
        var previousHour = -1
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                val currentTime = LocalTime.now()
                val currentHour = currentTime.hour % 12
                if (previousHour != currentHour) {
                    previousHour = currentHour
                    playSound(currentHour, twelveHourSoundResources)
                    Log.d("SoundPlayer", "Current time: ${currentTime.format(DateTimeFormatter.ofPattern("hh:mm a"))}")
                }
                delay(60_000) // Check every minute
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        Log.d("SoundPlayer", "Timer stopped")
    }
}

class TimeFormatObserver(
    context: Context,
    handler: Handler,
    private val onTimeFormatChanged: () -> Unit,
) : ContentObserver(handler) {
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        Log.d("TimeFormatObserver", "System time format changed")
        onTimeFormatChanged()
    }
}
