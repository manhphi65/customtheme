package com.example.customtheme

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val mapViewAnimation = HashMap<Int, Int>() //<View-Animation>.
        mapViewAnimation[R.id.iv_currency] = R.animator.anim_currency
        mapViewAnimation[R.id.iv_fuel] = R.animator.anim_fuel
        mapViewAnimation[R.id.iv_health] = R.animator.anim_health
        mapViewAnimation[R.id.iv_kilograms] = R.animator.anim_kilograms
        mapViewAnimation[R.id.iv_loan] = R.animator.anim_loan
        mapViewAnimation[R.id.iv_ovulation] = R.animator.anim_ovulation
        mapViewAnimation[R.id.iv_percentage] = R.animator.anim_percentage
        mapViewAnimation[R.id.iv_saving] = R.animator.anim_saving
        mapViewAnimation[R.id.iv_tax] = R.animator.anim_tax
        mapViewAnimation[R.id.iv_world_time] = R.animator.anim_world_time
        mapViewAnimation.keys.forEach { view ->
            findViewById<View>(view).startAnimation(
                AnimationUtils.loadAnimation(this, mapViewAnimation[view]!!)
            )
        }

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ finish() }, 5000)
    }

}