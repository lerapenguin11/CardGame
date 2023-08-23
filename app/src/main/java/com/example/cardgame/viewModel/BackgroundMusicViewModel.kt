package com.example.cardgame.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Vibrator
import androidx.lifecycle.ViewModel
import com.example.cardgame.R

class BackgroundMusicViewModel : ViewModel() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var backgroundMusic : MediaPlayer

    fun initBackgroundMusicSetting(context: Context) {
        //vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        backgroundMusic = MediaPlayer.create(context, R.raw.background_music)
        sharedPreferences = context.getSharedPreferences(GET_PREFS_KEY_BACKGROUND, Context.MODE_PRIVATE)
    }

    /*----------------- BACKGROUND MUSIC ------------------*/

    fun isBackgroundMusicEnabled() : Boolean{
        return sharedPreferences.getBoolean(PREFS_BACKGROUND_MUSIC_ENABLE, true)
    }

    fun setBackgroundMusicEnable(enabled: Boolean) : Boolean{
        sharedPreferences.edit().putBoolean(PREFS_BACKGROUND_MUSIC_ENABLE, enabled).apply()

        return sharedPreferences.getBoolean(PREFS_BACKGROUND_MUSIC_ENABLE, true)
    }

    fun backgroundMusic(context: Context){
        if (isBackgroundMusicEnabled()){
            //backgroundMusic = MediaPlayer.create(context, R.raw.background_music)
            backgroundMusic.start()
        } else{
            backgroundMusic.pause()
        }
    }

    fun stopMusic(){
        backgroundMusic.pause()
    }

    fun startMusic(){
        backgroundMusic.start()
    }

    /*----------------- BACKGROUND MUSIC ------------------*/
    companion object {
        private const val GET_PREFS_KEY_BACKGROUND = "settings_background"
        private const val PREFS_BACKGROUND_MUSIC_ENABLE = "background_music"
    }
}