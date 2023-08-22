package com.example.cardgame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cardgame.business.models.CardModel
import com.example.cardgame.databinding.FragmentGameBinding
import java.util.*
import kotlin.random.Random


class GameFragment : Fragment() {
    private var _binding : FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var deck: List<CardModel>
    private lateinit var selectedCard: CardModel

    private var score = 0
    private var attempts = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameBinding.inflate(inflater, container, false)

        initializeGame()

        return binding.root
    }

    private fun initializeGame() {
        // Создание колоды карт
        deck = createDeck()
        deck = deck.shuffled()

        val cards = deck.shuffled().take(15)
        val randomIndex = Random.nextInt(15)
        selectedCard = cards[randomIndex]
        // Выбор случайной карты для отображения

        binding.ivGuessCard.setImageResource(selectedCard.card)

        for (card in cards) {
            val imageView = ImageView(context)
            imageView.setImageResource(card.card)
            imageView.setOnClickListener {
                checkCard(card)
            }

            binding.cardGridLayout.addView(imageView)
        }
    }

    private fun createDeck(): List<CardModel> {
        val deck = mutableListOf<CardModel>()
        val cardValues = listOf(
            com.example.cardgame.R.drawable.card1, com.example.cardgame.R.drawable.card2,  com.example.cardgame.R.drawable.card3,  com.example.cardgame.R.drawable.card4,  com.example.cardgame.R.drawable.card5,
            com.example.cardgame.R.drawable.card6,  com.example.cardgame.R.drawable.card7,  com.example.cardgame.R.drawable.card8,  com.example.cardgame.R.drawable.card9,  com.example.cardgame.R.drawable.card10, com.example.cardgame.R.drawable.card11,
            com.example.cardgame.R.drawable.card12, com.example.cardgame.R.drawable.card13, com.example.cardgame.R.drawable.card14, com.example.cardgame.R.drawable.card15, com.example.cardgame.R.drawable.card16, com.example.cardgame.R.drawable.card17, com.example.cardgame.R.drawable.card18, com.example.cardgame.R.drawable.card19,
            com.example.cardgame.R.drawable.card20, com.example.cardgame.R.drawable.card21, com.example.cardgame.R.drawable.card22, com.example.cardgame.R.drawable.card23, com.example.cardgame.R.drawable.card24, com.example.cardgame.R.drawable.card25, com.example.cardgame.R.drawable.card26,
            com.example.cardgame.R.drawable.card27)

        for (card in cardValues) {

            deck.add(CardModel(card))
        }

        return deck
    }

    private fun checkCard(card: CardModel) {
        attempts++
        if (card == selectedCard) {
            score++
            Toast.makeText(context, "Правильно! Количество попыток: $attempts", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Неправильно! Указанная карта отличается. Количество попыток: $attempts", Toast.LENGTH_SHORT).show()
        }

        resetGame()
    }

    private fun resetGame() {
        binding.cardGridLayout.removeAllViews()
        initializeGame()
    }
}
