package com.example.cardgame.business.repos

interface CardGameRepository {
    suspend fun getBestResult(): Int
    suspend fun saveResult(result: Int)
}