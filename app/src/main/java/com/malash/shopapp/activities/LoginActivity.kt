package com.malash.shopapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.malash.shopapp.R

class LoginActivity : AppCompatActivity() {
    lateinit var signUpTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        addCallbacks()
    }

    private fun initView() {
        signUpTv = findViewById(R.id.tv_sign_up)
    }

    private fun addCallbacks() {
        signUpTv.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}