package com.go.it.planetapp.presentation.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.go.it.planetapp.App

class GothicRegular:TextView {
    constructor(context: Context?) : super(context){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }
    private fun init(){
        if (!isInEditMode) {
            typeface = App.centuryGothicRegular

        }
    }
}