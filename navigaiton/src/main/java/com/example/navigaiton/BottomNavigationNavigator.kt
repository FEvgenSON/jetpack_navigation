package com.example.navigaiton

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

    private companion object {
        const val IDS = "IDS"
        const val CLASS_NAMES = "CLASS_NAMES"
    }

    private val _backStack = mutableListOf<Destination>()
    val backStack: List<Destination>
        get() = _backStack

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? = navigate(destination, true)

    override fun popBackStack(): Boolean {
        _backStack.removeLast()
        navigate(_backStack.last(), false)
        return true
    }

    private fun navigate(
        destination: Destination,
        addToBackStack: Boolean
    ): Destination? {
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

        return when {
            !addToBackStack -> null
            _backStack.contains(destination) -> {
                _backStack.remove(destination)
                _backStack.add(destination)
                null
            }
            else -> {
                _backStack.add(destination)
                destination
            }
        }
    }

    override fun onSaveState(): Bundle? {
        return Bundle().apply {
            putIntArray(IDS, backStack.map { it.id }.toIntArray())
            putStringArray(CLASS_NAMES, backStack.map { it.className }.toTypedArray())
        }
    }

    override fun onRestoreState(savedState: Bundle?) {
        savedState?.let {
            val ids = it.getIntArray(IDS) ?: return
            val classNames = it.getStringArray(CLASS_NAMES) ?: return
            _backStack.clear()
            _backStack.addAll(
                ids.zip(classNames) { id: Int, className: String ->
                    Destination(this).apply {
                        setClassName(className)
                        setId(id)
                    }

                }
            )
        }
    }
}