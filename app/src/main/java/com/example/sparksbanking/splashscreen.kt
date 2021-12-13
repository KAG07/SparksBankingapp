package com.example.sparksbanking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.graphics.Color
import android.view.View

import gr.net.maroulis.library.EasySplashScreen




class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = EasySplashScreen(this@splashscreen)
            .withFullScreen()
            .withTargetActivity(MainScreen::class.java)
            .withSplashTimeOut(2500)
            .withBackgroundColor(Color.parseColor("#2e7d32"))
            .withHeaderText("Kunal Creation Presents")
            .withFooterText("@Copyright 2021")
            .withBeforeLogoText("SPARKS")
            .withAfterLogoText("BANKING")
            .withLogo(R.drawable.ic_baseline_stars_24)

        config.headerTextView.setTextColor(Color.WHITE)
        config.footerTextView.setTextColor(Color.WHITE)
        config.beforeLogoTextView.setTextColor(Color.WHITE)
        config.afterLogoTextView.setTextColor(Color.WHITE)
        config.beforeLogoTextView.setTextSize(40.0F)
        config.afterLogoTextView.setTextSize(40.0F)
        val easySplashScreen: View = config.create()
        setContentView(easySplashScreen)
    }
}