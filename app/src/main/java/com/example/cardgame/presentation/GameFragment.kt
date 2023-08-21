package com.example.cardgame.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import com.example.cardgame.R
import com.example.cardgame.business.models.CardModel
import com.example.cardgame.business.models.Constants
import com.example.cardgame.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var _binding : FragmentGameBinding? = null
    private val binding get() = _binding!!

    //private lateinit var selectedCardImageView: ImageView
    //private lateinit var cardGridLayout: GridLayout
    private lateinit var deck: List<CardModel>
    private lateinit var selectedCard: CardModel

    private var score = 0
    private var attempts = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //selectedCardImageView = requireView().findViewById(R.id.iv_guess_card)
        //cardGridLayout = requireView().findViewById(R.id.card_grid_layout)



        _binding = FragmentGameBinding.inflate(inflater, container, false)

        initializeGame()

        return binding.root
    }

    private fun initializeGame() {
        // Создание колоды карт
        deck = createDeck()
        deck = deck.shuffled()

        val a = deck.random()
        // Выбор случайной карты для отображения
        selectedCard = deck.random()
        println(selectedCard.card)
        binding.ivGuessCard.setImageResource(selectedCard.card)


        // Генерирование и отображение 15 случайных карт на экране
        repeat(15) {
            val card = deck.random()
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
        val cardValues = listOf(R.drawable.card1, R.drawable.card4,  R.drawable.card2,  R.drawable.card3,  R.drawable.card5,
            R.drawable.card6,  R.drawable.card7,  R.drawable.card8,  R.drawable.card10,  R.drawable.card9, R.drawable.card11,
            R.drawable.card1, R.drawable.card1, R.drawable.card1, R.drawable.card1, R.drawable.card1, R.drawable.card1, R.drawable.card1, R.drawable.card1,
            R.drawable.card12, R.drawable.card13, R.drawable.card1, R.drawable.card1, R.drawable.card1, R.drawable.card1, R.drawable.card1,
            R.drawable.card1)
        //var cards = Constants.getCardsList()

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
