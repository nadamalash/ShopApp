package com.malash.shopapp.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.malash.shopapp.R
import com.malash.shopapp.utils.progressDialog
import com.malash.shopapp.utils.showErrorSnackBar

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var emailTI: TextInputLayout
    private lateinit var emailEt: EditText
    private lateinit var forgotPassActionBar: Toolbar
    private lateinit var submitBtn: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        initialization()
        addCallbacks()
    }

    private fun initialization() {
        emailTI = findViewById(R.id.ti_email_forgot_password)
        emailEt = findViewById(R.id.et_email_forgot_password)
        submitBtn = findViewById(R.id.btn_submit_forgot_password)
        forgotPassActionBar = findViewById(R.id.action_bar_forgot_password)
        auth = Firebase.auth
        progressDialog = progressDialog(this@ForgotPasswordActivity)
    }

    private fun addCallbacks() {
        forgotPassActionBar.setNavigationOnClickListener {
            onBackPressed()
        }

        submitBtn.setOnClickListener {
            resetPassword(it)
        }
    }

    private fun emailIsValid(): Boolean {
        return when {
            (TextUtils.isEmpty(emailEt.text.toString().trim { it <= ' ' })) -> {
                emailTI.error = resources.getString(R.string.enter_email)
                false
            }
            else -> {
                emailTI.error = null
                true
            }
        }
    }


    private fun resetPassword(view: View) {
        //Check if the entries are valid or not
        if (emailIsValid()) {
            val email = emailEt.text.toString().trim { it <= ' ' }

            //Show progress dialog when email is valid
            progressDialog.show()

            //Reset password with email
            Firebase.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    //Hide progress dialog if process is successful or not
                    progressDialog.dismiss()
                    if (task.isSuccessful) {
                        showErrorSnackBar(
                            resources.getString(R.string.email_sent_to_reset_password_successfully),
                            false,
                            view,
                            this@ForgotPasswordActivity
                        )
                        //Back to Login after 2 sec
                        Handler().postDelayed({
                            finish()
                        }, 1900)
                    } else {
                        // If registration fails, display a message to the user
                        showErrorSnackBar(
                            resources.getString(R.string.email_failed_to_sent),
                            true,
                            view,
                            this@ForgotPasswordActivity
                        )
                    }
                }
        }
    }

}
