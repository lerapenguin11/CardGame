package com.example.cardgame.viewModel

import android.app.Application
import android.os.Vibrator
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AudioAndVibrationViewModel(application : Application) : AndroidViewModel(application) {

    fun vibratePhone() {
        viewModelScope.launch {
            val vibrator = getSystemService(getApplication(), Vibrator::class.java)
            vibrator!!.vibrate(500) // Длительность вибрации - 1000 миллисекунд (1 секунда)
        }
    }
}