package com.example.jetpacknaviagtion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_bottom.*

class BottomFragment : Fragment(R.layout.fragment_bottom) {

    private companion object {
        var instanceNumber = 0
    }

    init {
        instanceNumber++
    }

    private val localInstanceNumber = instanceNumber

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = instanceNumber.toString()
        full_screen_fragment_1_navigation_button.setOnClickListener {
            val action =
                BottomNavigationFragmentDirections.bottomNavigationFragmentToFullScreenFragment1(
                    FullScreenArg(
                        prevClass = "BottomNavigationFragment",
                        prevInstanceNumber = localInstanceNumber
                    )
                )
            requireActivity().findNavController(R.id.global_host).navigate(action)
        }
    }
}