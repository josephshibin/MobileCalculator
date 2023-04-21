package com.example.mobilecalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobilecalculator.databinding.FragmentVoiceAssistanceBinding
import com.example.mobilecalculator.viewmodel.MyViewModel

class VoiceAssistance : Fragment() {
    lateinit var binding: FragmentVoiceAssistanceBinding
    private lateinit var myViewModel: MyViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVoiceAssistanceBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        myViewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        myViewModel.toggleStateOfInputVoice.observe(viewLifecycleOwner) { isToggleButtonOn ->
            binding.switch3.isChecked = isToggleButtonOn
        }

        binding.switch3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                myViewModel.toggleStateOfInputVoice.value =isChecked

            }
        }
        return view

    }


}