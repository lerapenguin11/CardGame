package com.example.cardgame.business.repos

import com.example.cardgame.business.db.CardGameResult
import com.example.cardgame.business.db.CardGameResultDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardGameRepositoryImpl(private val database: CardGameResultDatabase) : CardGameRepository {

    override suspend fun getBestResult(): Int {
        return withContext(Dispatchers.IO) {
            database.cardGameResultDao().getBestResult()
        }
    }

    override suspend fun saveResult(result: Int) {
        val cardGameResult = CardGameResult(coins = result)
        withContext(Dispatchers.IO){
            database.cardGameResultDao().saveResult(cardGameResult)
        }
    }
}