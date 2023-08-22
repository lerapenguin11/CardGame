package com.example.cardgame.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.business.repos.CardGameRepositoryImpl

class CardGameViewModelFactory constructor(private val repository: CardGameRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {

        //super.create(modelClass)
        return return if (modelClass.isAssignableFrom(CardGameViewModel::class.java)) {
            CardGameViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}