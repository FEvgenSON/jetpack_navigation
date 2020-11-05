package com.example.bottom_host

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.navigaiton.BottomNavigationNavigator
import com.example.navigaiton.setupWithNavController
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    var navigator: BottomNavigationNavigator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.bottom_host) as NavHostFragment
        val navController = navHostFragment.navController
        if (navigator == null){
            navigator = BottomNavigationNavigator(
                context = requireContext(),
                manager = navHostFragment.childFragmentManager,
                containerId = R.id.bottom_host
            )
            navController.navigatorProvider.addNavigator(navigator!!)
            navController.setGraph(
                resources.getIdentifier(
                    "navigation_bottom",
                    "navigation",
                    requireActivity().packageName
                )
            )
        }
        bottom_navigation.setupWithNavController(navController, navigator!!)
    }
}