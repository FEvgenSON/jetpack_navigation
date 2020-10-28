package com.example.jetpacknaviagtion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        full_screen_fragment_1_navigation_button.setOnClickListener {
            findNavController().navigate(R.id.bottom_navigation_fragment_to_full_screen_fragment_1)
        }
    }
}