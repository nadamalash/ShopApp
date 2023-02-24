package com.malash.shopapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.malash.shopapp.R

class SignupActivity : AppCompatActivity() {
    lateinit var backBtn: ImageButton
    lateinit var signInTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initView()
        addCallbacks()
    }

    private fun initView() {
        backBtn = findViewById(R.id.btn_back)
        signInTv = findViewById(R.id.tv_sign_in)
    }

    private fun addCallbacks() {
        backBtn.setOnClickListener {
            onBackPressed()
        }
        signInTv.setOnClickListener {
            onBackPressed()
        }
    }
}