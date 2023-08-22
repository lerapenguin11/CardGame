package com.example.cardgame.business.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_game_res")
data class CardGameResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val coins: Int
)
