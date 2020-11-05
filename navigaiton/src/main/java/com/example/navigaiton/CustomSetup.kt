package com.example.navigaiton

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setupWithNavController(
    navController: NavController,
    navigator: BottomNavigationNavigator
) {
    setOnNavigationItemSelectedListener {
        navController.navigate(it.itemId)
        true
    }
    navController.addOnDestinationChangedListener { _: NavController, _: NavDestination, _: Bundle? ->
        val result = navigator.backStack.last()
        menu.findItem(result.id).isChecked = true
    }
}