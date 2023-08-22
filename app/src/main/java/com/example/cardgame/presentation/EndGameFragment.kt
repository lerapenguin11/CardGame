package com.example.cardgame.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.R
import com.example.cardgame.databinding.FragmentEndGameBinding
import com.example.cardgame.databinding.FragmentGameBinding
import com.example.cardgame.utilits.replaceFragmentMainActivityCardGame
import com.example.cardgame.viewModel.TimerViewModel

class EndGameFragment : Fragment() {
    private var _binding : FragmentEndGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var timerViewModel : TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEndGameBinding.inflate(inflater, container, false)

        timerViewModel  = ViewModelProvider(requireActivity()).get(TimerViewModel::class.java)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        onClick()
    }

    private fun onClick() {
        binding.btBackMenuEndGame.setOnClickListener {
            replaceFragmentMainActivityCardGame(MenuFragment())
        }

        binding.btRestart.setOnClickListener {
            timerViewModel.resetTimer()
            timerViewModel.startTimer()
            replaceFragmentMainActivityCardGame(GameFragment())
        }
    }
}