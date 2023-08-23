package com.example.cardgame.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel

class AudioAndVibrationViewModel() : ViewModel() {

    private lateinit var vibrator: Vibrator
    private lateinit var sharedPreferences: SharedPreferences

    fun initVibrationSetting(context: Context) {
        vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    fun isVibrationEnabled(): Boolean {
        return sharedPreferences.getBoolean("vibration_enabled", true)
    }

    fun setVibrationEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("vibration_enabled", enabled).apply()
    }

    fun vibrate() {
        if (isVibrationEnabled()) {
            val pattern = longArrayOf(0, 100)
            vibrator.vibrate(pattern, -1)
        }
    }
}