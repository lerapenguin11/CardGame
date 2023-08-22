package com.example.cardgame.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.business.db.CardGameResultDatabase
import com.example.cardgame.business.repos.CardGameRepositoryImpl
import com.example.cardgame.databinding.FragmentPauseBinding
import com.example.cardgame.utilits.replaceFragmentMainActivityCardGame
import com.example.cardgame.viewModel.CardGameViewModel
import com.example.cardgame.viewModel.CardGameViewModelFactory
import com.example.cardgame.viewModel.TimerViewModel

class PauseFragment : Fragment() {
    private var _binding : FragmentPauseBinding? = null
    private val binding get() = _binding!!

    private lateinit var timerViewModel : TimerViewModel
    private lateinit var coinsViewModel : CardGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPauseBinding.inflate(inflater, container, false)

        timerViewModel = ViewModelProvider(requireActivity()).get(TimerViewModel::class.java)

        val database = CardGameResultDatabase.getDatabase(requireContext())
        val repository = CardGameRepositoryImpl(database)
        val viewModelFactory = CardGameViewModelFactory(repository)
        coinsViewModel = ViewModelProvider(this, viewModelFactory).get(CardGameViewModel::class.java)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        val displayCurrent = arguments?.getString("totalCoins")
        val displayBestResult = arguments?.getString("bestResult")
        binding.tvCurrent.text = "score ${displayCurrent.toString()}"
        binding.tvBestScorePause.text = "best score ${displayBestResult.toString()}"
        onClick()
    }

    private fun onClick() {
        binding.btBackMenu.setOnClickListener {
            timerViewModel.resetTimer()
            replaceFragmentMainActivityCardGame(MenuFragment())
        }

        binding.btContinueGame.setOnClickListener {
            timerViewModel.resetTimer()
            replaceFragmentMainActivityCardGame(GameFragment())
        }
    }
}