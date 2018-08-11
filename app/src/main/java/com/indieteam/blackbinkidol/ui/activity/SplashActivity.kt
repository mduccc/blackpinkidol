package com.indieteam.blackbinkidol.ui.activity

import android.content.Intent
import android.graphics.Point
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.indieteam.blackbinkidol.R
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
        textView.textSize = 25f
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.measure(0,0)
        textView.x = sX*50 - textView.measuredWidth/2
        textView.y = sY*50 - textView.measuredHeight/2 - statusBarHeight
        rl_splash_activity.addView(textView)

        val intent = Intent(this, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 500)
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
