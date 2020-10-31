package com.example.jetpacknaviagtion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_full_screen1.*

class FullScreen1Fragment : Fragment(R.layout.fragment_full_screen1) {

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
        val currentInstance = "FullScreen1Fragment, instance number $localInstanceNumber"

        try {
            text.text = "$currentInstance " +
                    "\nPrev: ${safeArgs.prevFragmentData.prevClass}" +
                    " ${safeArgs.prevFragmentData.prevInstanceNumber}"
        } catch (throwable: Throwable) {
            text.text = "${arguments?.getString("type")} ${arguments?.getString("event")}"
        }
        //кнопки навигации
        full_screen_fragment_1_navigation_button.setOnClickListener {
            val action = FullScreen1FragmentDirections.fullScreenFragment1ToFullScreenFragment1(
                FullScreenArg(
                    prevClass = "FullScreen1Fragment",
                    prevInstanceNumber = localInstanceNumber
                )
            )
            findNavController().navigate(action)
        }
        full_screen_fragment_1_navigation_button2.setOnClickListener {
            val action =
                FullScreen1FragmentDirections.actionFullScreenFragment1ToFullScreenFragment2(
                    FullScreenArg(
                        prevClass = "FullScreen1Fragment",
                        prevInstanceNumber = localInstanceNumber
                    )
                )
            findNavController().navigate(action)
        }
        //кнопка назад
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().previousBackStackEntry?.savedStateHandle?.apply {
                set(
                    "prevArg", FullScreenArg(
                        prevClass = "FullScreen1Fragment",
                        prevInstanceNumber = localInstanceNumber
                    )
                )
            }
            findNavController().popBackStack()
        }
    }
}