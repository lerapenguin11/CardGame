package com.example.cardgame.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Vibrator
import androidx.lifecycle.ViewModel
import com.example.cardgame.R

class VibrationViewModel() : ViewModel() {

    private lateinit var vibrator: Vibrator
    private lateinit var sharedPreferences: SharedPreferences

    //private lateinit var backgroundMusic : MediaPlayer

    fun initVibrationSetting(context: Context) {
        vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        sharedPreferences = context.getSharedPreferences(GET_PREFS_KEY, Context.MODE_PRIVATE)
    }

    //------------------ VIBRATION ------------------

    fun isVibrationEnabled(): Boolean {
        return sharedPreferences.getBoolean(PREFS_VIBRATION_ENABLE, true)
    }

    fun setVibrationEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean(PREFS_VIBRATION_ENABLE, enabled).apply()
    }

    fun vibrate() {
        if (isVibrationEnabled()) {
            val pattern = longArrayOf(0, 100)
            vibrator.vibrate(pattern, -1)
        }
    }

    //------------------ VIBRATION ------------------

    companion object {
        private const val PREFS_VIBRATION_ENABLE = "vibration_enabled"
        private const val GET_PREFS_KEY = "settings"
        //private const val PREFS_BACKGROUND_MUSIC_ENABLE = "background_music"
    }
}