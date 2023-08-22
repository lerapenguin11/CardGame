package com.example.cardgame.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.databinding.FragmentGameBinding
import com.example.cardgame.databinding.FragmentPauseBinding
import com.example.cardgame.utilits.replaceFragmentMainActivityCardGame
import com.example.cardgame.viewModel.TimerViewModel

class PauseFragment : Fragment() {
    private var _binding : FragmentPauseBinding? = null
    private val binding get() = _binding!!

    private lateinit var timerViewModel : TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPauseBinding.inflate(inflater, container, false)

        timerViewModel = ViewModelProvider(requireActivity()).get(TimerViewModel::class.java)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        onCkick()
    }

    private fun onCkick() {
        binding.btBackMenu.setOnClickListener {
            timerViewModel.resetTimer()
            replaceFragmentMainActivityCardGame(MenuFragment())
        }

        binding.btContinueGame.setOnClickListener {
            timerViewModel.resumeTimer()
            replaceFragmentMainActivityCardGame(GameFragment())
        }
    }
}