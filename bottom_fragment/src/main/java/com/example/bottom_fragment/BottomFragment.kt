package com.example.bottom_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bottom_host.BottomNavigationFragmentDirections
import com.example.navigaiton.FullScreenArg
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
        text.text = localInstanceNumber.toString()
        full_screen_fragment_1_navigation_button.setOnClickListener {
            val action =
                BottomNavigationFragmentDirections.bottomHostToFullScreen(
                    FullScreenArg(
                        prevClass = "BottomNavigationFragment",
                        prevInstanceNumber = localInstanceNumber
                    )
                )
            requireActivity().findNavController(R.id.global_host).navigate(action)
        }
    }
}