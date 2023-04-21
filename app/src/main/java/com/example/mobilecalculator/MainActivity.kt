package com.example.mobilecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    fun onDigit(view: View) {
//        baseFragment.onDigit(view)
//    }
//
//    fun onOperator(view: View) {
//        baseFragment.onOperator(view)
//    }
//    fun onEqual(view: View) {
//        baseFragment.onEqual(view)
//    }
//    fun onDecimalPoint(view: View) {
//        baseFragment.onDecimalPoint(view)
//    }
//    fun onClear(view:View) {
//        baseFragment.onClear(view)
//    }

    //  below override function is used to navigate up in action bar
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerView3)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}