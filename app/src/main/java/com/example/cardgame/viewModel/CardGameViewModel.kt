package com.example.cardgame.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cardgame.business.repos.CardGameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CardGameViewModel(private val repository: CardGameRepository) : ViewModel() {

    private val _coins = MutableLiveData<Int>()


    private val _bestResult = MutableLiveData<Int>()


    init {
        val scope = CoroutineScope(Dispatchers.Main)
        _coins.value = 0
        scope.launch {
            _bestResult.value = repository.getBestResult()
        }

        //_bestResult.value = 0
    }

    fun incrementCoins() {
        _coins.value = (_coins.value ?: 0) + 1
    }

    suspend fun saveResult() = coroutineScope{
        launch {
            val currentResult = _coins.value ?: 0
            repository.saveResult(currentResult)
            if (currentResult > _bestResult.value ?: 0) {
                _bestResult.value = currentResult
            }
            //resetCoins()
        }
    }

    private fun resetCoins() {
        _coins.value = 0
    }
    val bestResult: LiveData<Int> = _bestResult
    val coins: LiveData<Int> = _coins
}
/*
class CardGameViewModel(application: Application) : AndroidViewModel(application) {
    private var cardGameDao: CardGameDao
    var cardGame : LiveData<CardGame> = MutableLiveData<CardGame>()

    init {
        val database: CardGameDatabase = CardGameDatabase.getDatabase(application)
        cardGameDao = database.cardGameDao()
        cardGame = cardGameDao.getCardGame()
    }

    fun increaseCoins() {
        val currentCoins = cardGame.value?.currentCoins ?: 0
        cardGame.value?.currentCoins = currentCoins + 1
        //cardGameDao.updateCardGame(cardGame.value!!)
    }

    fun updateBestCoins() {
        val currentCoins = cardGame.value?.currentCoins ?: 0
        val bestCoins = cardGame.value?.bestCoins ?: 0
        if (currentCoins > bestCoins) {
            cardGame.value?.bestCoins = currentCoins
            cardGameDao.updateCardGame(cardGame.value!!)
        }
    }

    fun resetCoins() {
        cardGame.value?.currentCoins = 0
        cardGameDao.updateCardGame(cardGame.value!!)
    }
}
*/

/*
class CardGameViewModel(*/
/*private val repository: CardGameRepository,*//*
 application: Application)
    : AndroidViewModel(application) {

    */
/*private val _coins = MutableLiveData<Int>()
    val coins: LiveData<Int>
        get() = _coins

    fun increaseCoins() {
        val currentCoins = _coins.value ?: 0
        _coins.value = currentCoins + 1
    }

    fun saveResult() {
        val currentCoins = _coins.value ?: 0
        repository.insertResult(CardGameResult(coins = currentCoins))
    }

    fun getBestResult(): CardGameResult {
        return repository.getBestResult()
    }

    fun getTotalCoins(): Int {
        return repository.getTotalCoins()
    }*//*


    private val cardGameDao: CardGameDao
    val cardGame: LiveData<CardGameDao>

    init {
        val database: CardGameDatabase = CardGameDatabase.getDatabase(application)
        cardGameDao = database.cardGameResultDao()
        cardGame = cardGameDao.getCardGame()
    }

    fun increaseCoins() {
        val currentCoins = cardGame.value ?: 0
        cardGame.value?.currentCoins = currentCoins + 1
        cardGameDao.updateCardGame(cardGame.value!!)
    }

    fun updateBestCoins() {
        val currentCoins = cardGame.value?.currentCoins ?: 0
        val bestCoins = cardGame.value?.bestCoins ?: 0
        if (currentCoins > bestCoins) {
            cardGame.value?.bestCoins = currentCoins
            cardGameDao.updateCardGame(cardGame.value!!)
        }
    }

    fun resetCoins() {
        cardGame.value?.currentCoins = 0
        cardGameDao.updateCardGame(cardGame.value!!)
    }
}*/
