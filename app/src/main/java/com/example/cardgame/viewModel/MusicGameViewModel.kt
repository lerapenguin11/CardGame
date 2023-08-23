package com.example.cardgame.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.cardgame.R

class MusicGameViewModel : ViewModel(){

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var musicGameWin : MediaPlayer

    fun initMusicGameSetting(context: Context) {
        musicGameWin = MediaPlayer.create(context, R.raw.music_game_win)
        sharedPreferences = context.getSharedPreferences(GET_PREFS_KEY_GAME, Context.MODE_PRIVATE)
    }

    fun isGameMusicEnabled() : Boolean{
        return sharedPreferences.getBoolean(PREFS_GAME_MUSIC_ENABLE, true)
    }

    fun setGameMusicEnable(enabled: Boolean){
        sharedPreferences.edit().putBoolean(PREFS_GAME_MUSIC_ENABLE, enabled).apply()
    }

    fun stopMusic(){
        musicGameWin.pause()
    }

    fun startMusic(){
        musicGameWin.start()
    }

    /*----------------- BACKGROUND MUSIC ------------------*/

    companion object {
        private const val GET_PREFS_KEY_GAME = "settings_game"
        private const val PREFS_GAME_MUSIC_ENABLE = "game_music"
    }
}