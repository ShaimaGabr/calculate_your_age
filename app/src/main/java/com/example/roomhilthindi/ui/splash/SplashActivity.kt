package com.example.roomhilthindi.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.roomhilthindi.R
import com.example.roomhilthindi.databinding.ActivityMainBinding
import com.example.roomhilthindi.databinding.ActivitySplashBinding
import com.example.roomhilthindi.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = bindingUI()
        setContentView(view)
        // run gif function.
        showGIF()
        moveToAllUser()
    }

    // add code to animate gif image.
    fun showGIF() {
       // val imageView: ImageView = findViewById(R.id.imageView)
        Glide.with(this).load(R.drawable.note).into(binding.imageView)
    }
    fun moveToAllUser(){

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
    private fun bindingUI(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        return binding.root
    }
}