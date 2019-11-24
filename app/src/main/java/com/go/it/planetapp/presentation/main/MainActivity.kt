package com.go.it.planetapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.go.it.planetapp.R
import com.go.it.planetapp.presentation.planetList.PlanetListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun navigateToPlanetList(view:View){
        startActivity(Intent(this@MainActivity,PlanetListActivity::class.java))
    }
}
