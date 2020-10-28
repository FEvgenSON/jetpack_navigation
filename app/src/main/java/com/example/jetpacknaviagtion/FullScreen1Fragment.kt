package com.example.jetpacknaviagtion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_full_screen1.*

class FullScreen1Fragment : Fragment(R.layout.fragment_full_screen1) {

    private companion object {
        var instanceNumber = 0
    }

    init {
        instanceNumber++
    }

    private val localInstanceNumber = instanceNumber

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = "FullScreen1Fragment, instance number $localInstanceNumber"
        full_screen_fragment_1_navigation_button.setOnClickListener {
            findNavController().navigate(R.id.full_screen_fragment_1_to_full_screen_fragment_1)
        }
    }
}