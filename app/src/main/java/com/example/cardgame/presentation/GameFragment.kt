package com.example.cardgame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.R
import com.example.cardgame.business.db.CardGameResultDatabase
import com.example.cardgame.business.models.CardModel
import com.example.cardgame.business.repos.CardGameRepositoryImpl
import com.example.cardgame.databinding.FragmentGameBinding
import com.example.cardgame.viewModel.VibrationViewModel
import com.example.cardgame.viewModel.CardGameViewModel
import com.example.cardgame.viewModel.CardGameViewModelFactory
import com.example.cardgame.viewModel.TimerViewModel
import kotlinx.coroutines.*
import java.util.*
import kotlin.random.Random


class GameFragment : Fragment() {
    private var _binding : FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var deck: List<CardModel>
    private lateinit var selectedCard: CardModel

    private var score = 0
    private var attempts = 0

    private lateinit var timerViewModel : TimerViewModel
    private lateinit var coinsViewModel : CardGameViewModel
    private lateinit var vibrationViewModel: VibrationViewModel

    private val scope = CoroutineScope(Dispatchers.Main)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameBinding.inflate(inflater, container, false)

        timerViewModel  = ViewModelProvider(requireActivity()).get(TimerViewModel::class.java)
        vibrationViewModel = ViewModelProvider(requireActivity()).get(VibrationViewModel::class.java)
        vibrationViewModel.initVibrationSetting(requireContext())

        val database = CardGameResultDatabase.getDatabase(requireContext())
        val repository = CardGameRepositoryImpl(database)
        val viewModelFactory = CardGameViewModelFactory(repository)
        coinsViewModel = ViewModelProvider(this, viewModelFactory).get(CardGameViewModel::class.java)

        coinsViewModel.coins.observe(requireActivity(), androidx.lifecycle.Observer { cardGame ->
            binding.tvCoins.text = cardGame.toString()
        })

        initializeGame()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        timerViewModel.startTimer()

        timerViewModel.timeLeftInMillis.observe(requireActivity(), androidx.lifecycle.Observer {
            updateTimer(it)
        })

        onClick()
    }

    private fun onClick() {
        binding.btPause.setOnClickListener {
            timerViewModel.pauseTimer()
            vibrationViewModel.vibrate()

            scope.launch {
                coinsViewModel.saveResult()
            }
            showResultScreen(bestResult = coinsViewModel.bestResult.value!!.toInt(),
                totalCoins = coinsViewModel.coins.value!!.toInt(),)
        }
    }

    private fun showResultScreen(bestResult: Int, totalCoins: Int) {
        val bundle = Bundle()
        bundle.putString("totalCoins", totalCoins.toString())
        bundle.putString("bestResult", bestResult.toString())
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = PauseFragment()
        fragment.arguments = bundle
        transaction?.replace(R.id.main_layout, fragment)
        transaction?.commit()
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
                if (timerViewModel.timeLeftInMillis.value != 0L){
                    checkCard(card)
                }
            }

            binding.cardGridLayout.addView(imageView)
        }
    }

    private fun closeCard() {
        scope.launch {
            coinsViewModel.saveResult()
        }
        vibrationViewModel.vibrate()

        showResultScreen(bestResult = coinsViewModel.bestResult.value!!.toInt(),
            totalCoins = coinsViewModel.coins.value!!.toInt(),)
        Toast.makeText(context, "Неправильно! Указанная карта отличается. Количество попыток: $attempts", Toast.LENGTH_SHORT).show()
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
        vibrationViewModel.vibrate()
        attempts++
        if (card == selectedCard) {
            score++
            coinsViewModel.incrementCoins()
            Toast.makeText(context, "Правильно! Количество попыток: $attempts", Toast.LENGTH_SHORT).show()
        } else {
            closeCard()
        }

        timerViewModel.resetTimer()
        timerViewModel.timeLeftInMillis.observe(requireActivity(), androidx.lifecycle.Observer {
            updateTimer(it)
        })

        resetGame()
    }

    private fun resetGame() {
        binding.cardGridLayout.removeAllViews()
        timerViewModel.startTimer()
        timerViewModel.timeLeftInMillis.observe(requireActivity(), androidx.lifecycle.Observer {
            updateTimer(it)
        })
        initializeGame()
    }

    //timer

    private fun updateTimer(millis: Long) {
        val seconds = (millis / 1000).toInt()
        val timeLeftFormatted = String.format(Locale.getDefault(),"%02d", seconds)

        binding.tvTimeGame.text = timeLeftFormatted

        if (seconds == 0){
            closeCard()
        }
    }
}
