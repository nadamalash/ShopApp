package com.malash.shopapp.utils

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

//Function to create Typeface ('Montserrat')
fun createTypeface(context: Context, fontStyle: String): Typeface =
    Typeface.createFromAsset(context.assets, "Montserrat-$fontStyle.ttf")

//Function to set full screen (without status bar)
fun hideStatusBar(activity: AppCompatActivity) {
    @Suppress("DEPRECATION")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
    } else {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}
