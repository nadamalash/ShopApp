package com.malash.shopapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.malash.shopapp.R

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

//Function to show snac kbar
fun showErrorSnackBar(message: String, isError: Boolean, view: View, activity: AppCompatActivity) {
    val snackBar = Snackbar.make(
        view,
        message,
        Snackbar.LENGTH_SHORT
    )
    snackBar.setTextColor(ContextCompat.getColor(activity, R.color.white))
    val snackBarView = snackBar.view
    if (isError)
        snackBarView.setBackgroundColor(ContextCompat.getColor(activity, R.color.error))
    else
        snackBarView.setBackgroundColor(ContextCompat.getColor(activity, R.color.success))
    snackBar.show()
}

//Function to show progress dialog
fun progressDialog(activity: AppCompatActivity): Dialog {
    val dialog = Dialog(activity)
    //Set the screen content from layout resources
    dialog.setContentView(R.layout.dialog_progress)

    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(false)
    return dialog
}

fun backAfterTwoSec(activity: AppCompatActivity) {
    @Suppress("DEPRECATION")
    Handler().postDelayed({
        activity.finish()
    }, 1900)
}

