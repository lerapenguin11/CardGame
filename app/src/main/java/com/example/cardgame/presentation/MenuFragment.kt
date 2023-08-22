package com.example.cardgame.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.R
import com.example.cardgame.databinding.FragmentMenuBinding
import com.example.cardgame.utilits.replaceFragmentMainActivityCardGame
import com.example.cardgame.viewModel.TimerViewModel

class MenuFragment : Fragment() {
    private var _binding : FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        viewModel  = ViewModelProvider(requireActivity()).get(TimerViewModel::class.java)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        onClick()
    }

    private fun onClick() {
        binding.btStart.setOnClickListener {
            replaceFragmentMainActivityCardGame(GameFragment())

        }
    }
}