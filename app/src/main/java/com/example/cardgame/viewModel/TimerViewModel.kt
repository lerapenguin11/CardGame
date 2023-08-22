package com.example.cardgame.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private val _timeLeftInMillis = MutableLiveData<Long>()
    val timeLeftInMillis: LiveData<Long> = _timeLeftInMillis

    private var timerJob: Job? = null
    private var isTimerRunning = false
    private var initialTimeInMillis = 10000L

    fun startTimer() {
        if (!isTimerRunning) {
            timerJob = viewModelScope.launch {
                for (i in initialTimeInMillis / 1000 downTo 0) {
                    _timeLeftInMillis.postValue(i.toLong() * 1000)
                    delay(1000)
                }
            }
            isTimerRunning = true
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        isTimerRunning = false
    }

    fun resumeTimer() {
        if (!isTimerRunning) {
            timerJob = viewModelScope.launch {
                val currentMillis = _timeLeftInMillis.value ?: initialTimeInMillis
                for (i in currentMillis / 1000 downTo 0) {
                    _timeLeftInMillis.postValue(i.toLong() * 1000)
                    delay(1000)
                }
            }
            isTimerRunning = true
        }
    }

    fun resetTimer() {
        timerJob?.cancel()
        isTimerRunning = false
        _timeLeftInMillis.value = initialTimeInMillis
    }
}