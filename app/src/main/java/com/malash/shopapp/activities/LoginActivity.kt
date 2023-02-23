package com.malash.shopapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.malash.shopapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Just for test
        val login = findViewById<Button>(R.id.btn_login)
        login.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}