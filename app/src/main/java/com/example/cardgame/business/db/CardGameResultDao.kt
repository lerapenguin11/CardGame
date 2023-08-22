package com.example.cardgame.business.db

import androidx.room.*

@Dao
interface CardGameResultDao {
    @Query("SELECT MAX(coins) FROM card_game_res")
    fun getBestResult(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResult(result: CardGameResult)
}
/*
@Dao
interface CardGameDao {
    */
/*@Insert
    fun insert(result: CardGameResult)

    @Query("SELECT * FROM card_game_results ORDER BY coins DESC LIMIT 1")
    fun getBestResult(): CardGameResult

    @Query("SELECT SUM(coins) FROM card_game_results")
    fun getTotalCoins(): Int*//*


    */
/*@Query("SELECT * FROM card_game LIMIT 1")
    fun getCardGame(): LiveData<CardGame>

    @Update
    fun updateCardGame(cardGame: CardGame)*//*



}*/
