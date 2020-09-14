package com.alphacorporations.moviesgallery.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.alphacorporations.moviesgallery.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
Created by Alph4 le 10/09/2020.
Projet: Movies Gallery
 **/
class SplashActivity : AppCompatActivity() {

    private val SPLAH_SCREEN: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        logo_splash.startAnimation(AnimationUtils.loadAnimation(this, R.anim.top_animation))
        text_splash.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_animation))

        val intentListMovie = Intent(this, MainActivity::class.java)

        val handler = Handler()
        val runnable = Runnable {
            startActivity(intentListMovie)
            finish()
        }
        handler.postDelayed(runnable, SPLAH_SCREEN)
    }
}