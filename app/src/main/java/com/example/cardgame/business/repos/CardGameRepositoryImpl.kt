package com.example.cardgame.business.repos

import com.example.cardgame.business.db.CardGameResult
import com.example.cardgame.business.db.CardGameResultDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardGameRepositoryImpl(private val database: CardGameResultDatabase) : CardGameRepository {

    override suspend fun getBestResult(): Int {
        return withContext(Dispatchers.IO) {
            // Здесь выполняется запрос к базе данных
            database.cardGameResultDao().getBestResult()
        }
        //return database.cardGameResultDao().getBestResult() ?: 0
    }

    override suspend fun saveResult(result: Int) {
        val cardGameResult = CardGameResult(coins = result)
        withContext(Dispatchers.IO){
            database.cardGameResultDao().saveResult(cardGameResult)
        }

    }
}

/*class CardGameRepository(private val cardGameDao: CardGameDao) {
    val cardGame: LiveData<CardGame> = cardGameDao.getCardGame()

    suspend fun updateCardGame(cardGame: CardGame) {
        cardGameDao.updateCardGame(cardGame)
    }
}*/

/*fun insertResult(result: CardGameResult) {
        cardGameResultDao.insert(result)
    }

    fun getBestResult(): CardGameResult {
        return cardGameResultDao.getBestResult()
    }

    fun getTotalCoins(): Int {
        return cardGameResultDao.getTotalCoins()
    }*/