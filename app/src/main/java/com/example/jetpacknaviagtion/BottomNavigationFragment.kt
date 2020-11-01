package com.example.jetpacknaviagtion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.bottom_host) as NavHostFragment
        val navController = navHostFragment.navController
        val navigator = try {
            navController.navigatorProvider.getNavigator(BottomNavigationNavigator::class.java)
        } catch (exception: IllegalStateException) {
            BottomNavigationNavigator(
                context = requireContext(),
                manager = navHostFragment.childFragmentManager,
                containerId = R.id.bottom_host
            )
        }
        navController.navigatorProvider.addNavigator(navigator)
        navController.setGraph(R.navigation.navigation_bottom)
        bottom_navigation.setupWithNavController(navController, navigator)
    }
}