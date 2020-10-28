package com.example.jetpacknaviagtion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    private companion object {
        var instanceNumber = 0
    }

    init {
        instanceNumber++
    }

    private val localInstanceNumber = instanceNumber

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = "BottomNavigationFragment, instance number $localInstanceNumber"
        full_screen_fragment_1_navigation_button.setOnClickListener {
            findNavController().navigate(R.id.bottom_navigation_fragment_to_full_screen_fragment_1)
        }
    }
}