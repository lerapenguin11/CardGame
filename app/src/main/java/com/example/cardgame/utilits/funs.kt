package com.example.cardgame.utilits

import androidx.fragment.app.Fragment
import com.example.cardgame.APP_MAIN_ACTIVITY_CARD_GAME
import com.example.cardgame.R

fun replaceFragmentMainActivityCardGame(fragm: Fragment, st: Boolean = true) {
    if (st) {
        APP_MAIN_ACTIVITY_CARD_GAME.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.main_layout,
                fragm
            ).commit()
    } else {
        APP_MAIN_ACTIVITY_CARD_GAME.supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_layout,
                fragm
            ).commit()
    }
}