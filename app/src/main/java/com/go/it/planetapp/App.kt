package com.go.it.planetapp

import android.app.Application
import android.graphics.Typeface

class App :Application() {
    companion object{

        lateinit var centuryGothicRegular: Typeface
        lateinit var centuryGothicLight: Typeface

    }
    override fun onCreate() {
        super.onCreate()

        initTypefaces()


    }

    private fun initTypefaces() {
        centuryGothicLight =Typeface.createFromAsset(assets,"font/gothic_light.otf")
        centuryGothicRegular =Typeface.createFromAsset(assets,"font/gothic_regular.otf")
    }
}