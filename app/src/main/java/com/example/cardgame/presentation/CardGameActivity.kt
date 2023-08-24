package com.example.cardgame.presentation

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.cardgame.APP_MAIN_ACTIVITY_CARD_GAME
import com.example.cardgame.R
import com.example.cardgame.databinding.ActivityMainBinding
import com.example.cardgame.utilits.replaceFragmentMainActivityCardGame

class CardGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_MAIN_ACTIVITY_CARD_GAME = this
    }

    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarMainActivityGradiant(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val backgroundActivity = ContextCompat.getDrawable(activity, R.drawable.background)
            val windowActivity: Window = activity.window
            windowActivity.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            windowActivity.statusBarColor = ContextCompat.getColor(activity,android.R.color.transparent)
            windowActivity.setBackgroundDrawable(backgroundActivity)
        } else{
            ///
        }
    }

    override fun onStart() {
        super.onStart()
        setStatusBarMainActivityGradiant(this)
        replaceFragmentMainActivityCardGame(MenuFragment())
    }
}