package com.example.usahaku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.usahaku.databinding.FragmentInfoBinding

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class fraginfo : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_info,container,false)
        // Inflate the layout for this fragment
        return binding.root

    }
}