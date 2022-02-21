package com.codepath.skylinepropertyanimationdemo

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.RelativeLayout
import android.os.Bundle
import com.codepath.skylinepropertyanimationdemo.R
import android.util.DisplayMetrics
import android.view.animation.AccelerateInterpolator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.widget.ImageView

class SkylinePropertyAnimationActivity : AppCompatActivity() {
    private var ivWheel: ImageView? = null
    private var ivSun: ImageView? = null
    private var rlSkyLayout: RelativeLayout? = null
    private var ivCloud1: ImageView? = null
    private var ivCloud2: ImageView? = null
    private var ivBird: ImageView? = null
    private val ANIMATION_DURATION = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skyline_property_animation)
        // Lookup views
        setupViews()
        // Animate the wheel with rotation
        animateWheel()
        // Animate the sun in sky
        animateSun()
        // Darken sky between day and night
        darkenSky()
        // Move clouds around
        moveClouds()
        // Animate bird
        animateBird()
        // Transition action bar
        animateActionBar()
    }

    private fun setupViews() {
        ivWheel = findViewById<View>(R.id.ivWheel) as ImageView
        ivSun = findViewById<View>(R.id.ivSun) as ImageView
        rlSkyLayout = findViewById<View>(R.id.rlSkyLayout) as RelativeLayout
        ivCloud1 = findViewById<View>(R.id.ivCloud1) as ImageView
        ivCloud2 = findViewById<View>(R.id.ivCloud2) as ImageView
        ivBird = findViewById<View>(R.id.ivBird) as ImageView
    }

    fun animateWheel() {
        //load the wheel animation
        val wheelSet = AnimatorInflater.loadAnimator(this,
                R.animator.wheel_spin) as AnimatorSet
        //set the view as target
        wheelSet.setTarget(ivWheel)
        //start the animation
        wheelSet.start()
    }

    fun animateSun() {
        //load the sun movement animation
        val sunSet = AnimatorInflater.loadAnimator(this,
                R.animator.sun_swing) as AnimatorSet
        //set the view as target
        sunSet.setTarget(ivSun)
        //start the animation
        sunSet.start()
    }

    fun darkenSky() {
        //darken sky
        val skyAnim: ValueAnimator = ObjectAnimator.ofInt(rlSkyLayout, "backgroundColor",
                Color.parseColor("#66ccff"),
                Color.parseColor("#006699"))
        skyAnim.duration = ANIMATION_DURATION.toLong()
        skyAnim.repeatCount = ValueAnimator.INFINITE
        skyAnim.repeatMode = ValueAnimator.REVERSE
        skyAnim.setEvaluator(ArgbEvaluator())
        skyAnim.start()
    }

    fun moveClouds() {
        //move clouds
        val cloudAnim = ObjectAnimator.ofFloat(ivCloud1, View.X, -350f)
        cloudAnim.duration = ANIMATION_DURATION.toLong()
        cloudAnim.repeatCount = ValueAnimator.INFINITE
        cloudAnim.repeatMode = ValueAnimator.REVERSE
        cloudAnim.start()
        // other cloud
        val cloud2Set = AnimatorSet()
        val cloudXAnim2 = ObjectAnimator.ofFloat(ivCloud2, View.X, -300f)
        cloudXAnim2.duration = ANIMATION_DURATION.toLong()
        cloudXAnim2.repeatCount = ValueAnimator.INFINITE
        cloudXAnim2.repeatMode = ValueAnimator.REVERSE
        val cloudYAnim2 = ObjectAnimator.ofFloat(ivCloud2, View.TRANSLATION_Y, -200f)
        cloudYAnim2.duration = ANIMATION_DURATION.toLong()
        cloudYAnim2.repeatCount = ValueAnimator.INFINITE
        cloudYAnim2.repeatMode = ValueAnimator.REVERSE
        cloud2Set.playSequentially(cloudXAnim2, cloudYAnim2)
        cloud2Set.start()
    }

    private fun animateBird() {
        ivBird!!.x = -200f
        // Get screen width
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            val insets =
//                windowManager.currentWindowMetrics.windowInsets.getInsetsIgnoringVisibility(
//                    WindowInsets.Type.systemBars()
//                )
//            windowManager.currentWindowMetrics.bounds.width() - insets.left - insets.right
//        } else {
//            windowManager.defaultDisplay.getMetrics(metrics)
//            metrics.widthPixels
//        }

        // Animate bird off screen to the right
        ivBird!!.animate().x(metrics.widthPixels + 50.toFloat()).setStartDelay(1000).setDuration(ANIMATION_DURATION.toLong())
            .setInterpolator(AccelerateInterpolator()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animateBird()
            }
        }).start()
    }

    private fun animateActionBar() {
        val colorFrom = Color.parseColor("#66ccff")
        val colorTo = Color.parseColor("#006699")
        val actionBarColorAnim = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)

        actionBarColorAnim.duration = ANIMATION_DURATION.toLong() // milliseconds
        actionBarColorAnim.repeatCount = ValueAnimator.INFINITE
        actionBarColorAnim.repeatMode = ValueAnimator.REVERSE
        actionBarColorAnim.addUpdateListener { animator -> supportActionBar!!.setBackgroundDrawable(ColorDrawable(animator.animatedValue as Int)) }
        actionBarColorAnim.start()
    }
}