package com.example.cardgame.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.cardgame.R
import com.example.cardgame.databinding.FragmentMenuBinding
import com.example.cardgame.utilits.replaceFragmentMainActivityCardGame
import com.example.cardgame.viewModel.BackgroundMusicViewModel
import com.example.cardgame.viewModel.VibrationViewModel
import com.example.cardgame.viewModel.TimerViewModel

class MenuFragment : Fragment() {
    private var _binding : FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var timerViewModel : TimerViewModel
    private lateinit var vibrationViewModel: VibrationViewModel
    private lateinit var backgroundMusicViewModel: BackgroundMusicViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        timerViewModel  = ViewModelProvider(requireActivity()).get(TimerViewModel::class.java)

        vibrationViewModel = ViewModelProvider(requireActivity()).get(VibrationViewModel::class.java)

        backgroundMusicViewModel = ViewModelProvider(requireActivity()).get(BackgroundMusicViewModel::class.java)

        //vibration
        vibrationViewModel.initVibrationSetting(requireContext())

        binding.viewModel = vibrationViewModel
        binding.lifecycleOwner = this

        binding.btVibration.setOnClickListener {
            if (vibrationViewModel.isVibrationEnabled()){
                backgroundMusicViewModel.stopMusic()
                binding.icVibration.setImageResource(R.drawable.ic_vibra)
                vibrationViewModel.setVibrationEnabled(false)

            } else{
                binding.icVibration.setImageResource(R.drawable.ic_not_vibra)
                vibrationViewModel.setVibrationEnabled(true)
            }
        }
        //vibration

        //background music
        backgroundMusicViewModel.initBackgroundMusicSetting(requireContext())

        binding.backgroundMusicViewModel = backgroundMusicViewModel
        binding.lifecycleOwner = this

        binding.btBackgroundMusic.setOnClickListener {
            vibrationViewModel.vibrate()

            if (backgroundMusicViewModel.isBackgroundMusicEnabled()){

                binding.icMusicBackground.setImageResource(R.drawable.ic_bg_music)
                backgroundMusicViewModel.setBackgroundMusicEnable(false)
                backgroundMusicViewModel.stopMusic()
            } else{
                backgroundMusicViewModel.startMusic()
                binding.icMusicBackground.setImageResource(R.drawable.ic_not_bg_music)
                backgroundMusicViewModel.setBackgroundMusicEnable(true)
            }
        }
        //background music


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        backgroundMusicViewModel.startMusic()

        onClick()
    }

    @SuppressLint("NewApi")
    private fun onClick() {
        binding.btStart.setOnClickListener {
            backgroundMusicViewModel.stopMusic()
            vibrationViewModel.vibrate()
            replaceFragmentMainActivityCardGame(GameFragment())
        }
    }
}