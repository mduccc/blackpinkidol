package com.indieteam.blackpinkidol.ui.activity

import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.process.GetAllDataHttp
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    var sX = 0f
    var sY = 0f
    var statusBarHeight = 0
    var navigationBarHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getScreen()

        val textView = TextView(this)
        textView.text = "BLΛƆKPIИK"
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.textSize = 21f
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.measure(0,0)
        textView.x = sX*50 - textView.measuredWidth/2
        textView.y = sY*50 - textView.measuredHeight/2 - statusBarHeight

        val progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal)
        progressBar.isIndeterminate = true
        progressBar.background = resources.getDrawable(R.color.colorBlack)
        progressBar.indeterminateDrawable.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_ATOP)
        progressBar.measure(0,0)
        progressBar.x = sX*50 - progressBar.measuredWidth/2
        progressBar.y = textView.y + textView.measuredHeight + sY*5 - progressBar.measuredHeight/2 - statusBarHeight
        rl_splash_activity.addView(textView)
        rl_splash_activity.addView(progressBar)

        GetAllDataHttp(this).get()
    }

    private fun getScreen(){
        val manager = windowManager.defaultDisplay
        val point  = Point()
        manager.getSize(point)
        sX = point.x/100f
        sY = point.y/100f

        val resources = resources
        val resourcesId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val resourcesId2 = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        statusBarHeight = resources.getDimensionPixelSize(resourcesId)
        navigationBarHeight = resources.getDimensionPixelSize(resourcesId2)
    }
}
