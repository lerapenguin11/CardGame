package com.example.cardgame.business.db

import androidx.room.*

@Dao
interface CardGameResultDao {
    @Query("SELECT MAX(coins) FROM card_game_res")
    fun getBestResult(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResult(result: CardGameResult)
}
