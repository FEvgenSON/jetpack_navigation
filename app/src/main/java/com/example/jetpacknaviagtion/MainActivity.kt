package com.example.jetpacknaviagtion

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        var instance = 0
    }

    init {
        instance++
    }

	val localInstance = instance

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("CheckActivity", "im create $localInstance")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        Log.d("CheckActivity", "im resume  $localInstance")
        super.onResume()
    }

    override fun onPause() {
        Log.d("CheckActivity", "im pause $localInstance")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("CheckActivity", "im destroy $localInstance")
        super.onDestroy()
    }
}