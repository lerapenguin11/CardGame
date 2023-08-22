package com.example.cardgame.business.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardGameResult::class], version = 3)
abstract class CardGameResultDatabase : RoomDatabase() {
    abstract fun cardGameResultDao(): CardGameResultDao

    companion object {
        @Volatile
        private var INSTANCE: CardGameResultDatabase? = null

        fun getDatabase(context: Context): CardGameResultDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardGameResultDatabase::class.java,
                    "card_game_database_4"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}