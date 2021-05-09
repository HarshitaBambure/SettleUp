package com.example.settleup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.settleup.helper.Constants.PREF_SETTLE_UP

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                finish()
                if (onBoardingFinished()) {
                    startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, SliderActivity::class.java))
                }
            }

        }, 2000
        )
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences(PREF_SETTLE_UP, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)


    }
}
