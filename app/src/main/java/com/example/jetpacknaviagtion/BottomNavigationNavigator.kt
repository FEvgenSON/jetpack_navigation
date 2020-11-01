package com.example.jetpacknaviagtion

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

@Navigator.Name("BottomFragment")
class BottomNavigationNavigator(
    private val manager: FragmentManager,
    private val context: Context,
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    private val backStack = mutableListOf<Destination>()

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {

        navigate(destination, true)
        return destination
    }

    override fun popBackStack(): Boolean {
        backStack.removeLast()
        navigate(backStack.last(), false)
        return true
    }

    private fun navigate(
        destination: Destination,
        addToBackStack: Boolean
    ) {
        val destinationClassName = destination.className
        val destinationId = destination.id.toString()
        val transaction = manager.beginTransaction()
        val currentFragment = manager.primaryNavigationFragment

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        var fragment = manager.findFragmentByTag(destinationId)
        if (fragment == null) {
            fragment =
                manager.fragmentFactory.instantiate(context.classLoader, destinationClassName)
            transaction.add(containerId, fragment, destinationId)
        } else {
            transaction.show(fragment)
        }

        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commit()

        if (addToBackStack) {
            backStack.add(destination)
        }
    }
}