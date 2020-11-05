package com.example.jetpacknaviagtion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigaiton.FullScreenArg
import kotlinx.android.synthetic.main.fragment_full_screen2.*

class FullScreenFragment2 : Fragment(R.layout.fragment_full_screen2){

    private companion object {
        var instanceNumber = 0
    }

    init {
        instanceNumber++
    }

    private val localInstanceNumber = instanceNumber

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //safe args и текст
        val safeArgs by navArgs<FullScreen1FragmentArgs>()
        val currentInstance = "FullScreen2Fragment, instance number $localInstanceNumber"

        text.text = "$currentInstance " +
                "\nPrev: ${safeArgs.prevFragmentData.prevClass}" +
                " ${safeArgs.prevFragmentData.prevInstanceNumber}"
        //кнопки навигации
        full_screen_fragment_1_navigation_button.setOnClickListener {
            val action = FullScreenFragment2Directions.fullScreenFragment2ToBottomNavigationFragment(
                FullScreenArg(
                    prevClass = "FullScreen2Fragment",
                    prevInstanceNumber = localInstanceNumber
                )
            )
            findNavController().navigate(action)
        }
    }
}