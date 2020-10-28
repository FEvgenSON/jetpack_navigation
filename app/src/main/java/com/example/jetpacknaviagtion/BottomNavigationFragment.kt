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
        val backStackResult =
            findNavController().currentBackStackEntry?.savedStateHandle?.get<FullScreenArg>("prevArg")
        findNavController().currentBackStackEntry?.savedStateHandle?.remove<FullScreenArg>("prevArg")

        var instanceText = "BottomNavigationFragment, instance number $localInstanceNumber"
        backStackResult?.let {
            instanceText += "\nPrev: ${it.prevClass} ${it.prevInstanceNumber}"
        }
        text.text = instanceText
        full_screen_fragment_1_navigation_button.setOnClickListener {
            val action =
                BottomNavigationFragmentDirections.bottomNavigationFragmentToFullScreenFragment1(
                    FullScreenArg(
                        prevClass = "BottomNavigationFragment",
                        prevInstanceNumber = localInstanceNumber
                    )
                )
            findNavController().navigate(action)
        }
    }
}