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
import com.example.cardgame.R
import com.example.cardgame.databinding.FragmentMenuBinding
import com.example.cardgame.utilits.replaceFragmentMainActivityCardGame
import com.example.cardgame.viewModel.AudioAndVibrationViewModel
import com.example.cardgame.viewModel.TimerViewModel

class MenuFragment : Fragment() {
    private var _binding : FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var timerViewModel : TimerViewModel
    private lateinit var vibrationViewModel: AudioAndVibrationViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        timerViewModel  = ViewModelProvider(requireActivity()).get(TimerViewModel::class.java)

        vibrationViewModel = ViewModelProvider(requireActivity()).get(AudioAndVibrationViewModel::class.java)
        vibrationViewModel.initVibrationSetting(requireContext())

        binding.viewModel = vibrationViewModel
        binding.lifecycleOwner = this

        binding.btVibration.setOnClickListener {
            if (vibrationViewModel.isVibrationEnabled()){
                binding.icVibration.setImageResource(R.drawable.ic_vibra)
                vibrationViewModel.setVibrationEnabled(false)

            } else{
                binding.icVibration.setImageResource(R.drawable.ic_not_vibra)
                vibrationViewModel.setVibrationEnabled(true)
            }
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()

        onClick()
    }

    @SuppressLint("NewApi")
    private fun onClick() {
        binding.btStart.setOnClickListener {
            vibrationViewModel.vibrate()
            replaceFragmentMainActivityCardGame(GameFragment())
        }
    }
}