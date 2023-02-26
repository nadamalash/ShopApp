package com.malash.shopapp.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.malash.shopapp.R
import com.malash.shopapp.utils.progressDialog
import com.malash.shopapp.utils.showErrorSnackBar

class LoginActivity : AppCompatActivity() {
    private lateinit var emailTI: TextInputLayout
    private lateinit var passwordTI: TextInputLayout

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var forgotPasswordTv: TextView
    private lateinit var signUpTv: TextView
    private lateinit var loginBtn: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialization()
        addCallbacks()
    }

    private fun initialization() {
        emailTI = findViewById(R.id.ti_email_login)
        passwordTI = findViewById(R.id.ti_password_login)
        emailEt = findViewById(R.id.et_email_login)
        passwordEt = findViewById(R.id.et_password_login)
        signUpTv = findViewById(R.id.tv_sign_up)
        forgotPasswordTv = findViewById(R.id.tv_forgot_password)
        loginBtn = findViewById(R.id.btn_login)
        auth = Firebase.auth
        progressDialog = progressDialog(this@LoginActivity)
    }

    private fun addCallbacks() {
        signUpTv.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        loginBtn.setOnClickListener {
            loginRegisteredUser(it)
        }
        forgotPasswordTv.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    private fun validateLoginDetails(): Boolean {
        var isValid = true
        //Email
        if (TextUtils.isEmpty(emailEt.text.toString().trim { it <= ' ' })) {
            emailTI.error = resources.getString(R.string.enter_email)
            isValid = false
        } else {
            emailTI.error = null
        }
        //Password
        if (TextUtils.isEmpty(passwordEt.text.toString().trim { it <= ' ' })) {
            passwordTI.error = resources.getString(R.string.enter_password)
            isValid = false
        } else {
            passwordTI.error = null
        }
        return isValid
    }

    private fun loginRegisteredUser(view: View) {
        //Check if the entries are valid or not
        if (validateLoginDetails()) {
            val email = emailEt.text.toString().trim { it <= ' ' }
            val password = passwordEt.text.toString().trim { it <= ' ' }

            //Show progress dialog when entries are valid
            progressDialog.show()
            //Registered user sign in with email and password
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    //Hide progress dialog if sign in is successful or not
                    progressDialog.dismiss()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        showErrorSnackBar(
                            resources.getString(R.string.login_successful),
                            false,
                            view,
                            this@LoginActivity
                        )
                    } else {
                        // If sign in fails, display a message to the user
                        showErrorSnackBar(
                            resources.getString(R.string.login_failed),
                            true,
                            view,
                            this@LoginActivity
                        )
                    }
                }
        }
    }
}