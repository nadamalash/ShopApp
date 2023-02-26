package com.malash.shopapp.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.malash.shopapp.R
import com.malash.shopapp.utils.showErrorSnackBar
import com.malash.shopapp.utils.progressDialog

class SignupActivity : AppCompatActivity() {
    private lateinit var firstNameTI: TextInputLayout
    private lateinit var lastNameTI: TextInputLayout
    private lateinit var emailTI: TextInputLayout
    private lateinit var passwordTI: TextInputLayout
    private lateinit var passwordConfirmTI: TextInputLayout

    private lateinit var firstNameEt: EditText
    private lateinit var lastNameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var passwordConfirmEt: EditText

    private lateinit var agreeTermsCb: CheckBox
    private lateinit var agreeTermsTv: TextView
    private lateinit var agreeTermsErrorTv: TextView
    private lateinit var signupBtn: Button
    private lateinit var signinTv: TextView
    private lateinit var signupActionBar: Toolbar

    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initialization()
        addCallbacks()
    }

    private fun initialization() {
        firstNameTI = findViewById(R.id.ti_first_name)
        lastNameTI = findViewById(R.id.ti_last_name)
        emailTI = findViewById(R.id.ti_email_signup)
        passwordTI = findViewById(R.id.ti_password_signup)
        passwordConfirmTI = findViewById(R.id.ti_password_confirm_signup)

        firstNameEt = findViewById(R.id.et_first_name)
        lastNameEt = findViewById(R.id.et_last_name)
        emailEt = findViewById(R.id.et_email_signup)
        passwordEt = findViewById(R.id.et_password_signup)
        passwordConfirmEt = findViewById(R.id.et_password_confirm_signup)

        agreeTermsCb = findViewById(R.id.checkbox_agree_terms)
        agreeTermsTv = findViewById(R.id.tv_agree_terms)
        agreeTermsErrorTv = findViewById(R.id.tv_agree_error_msg)
        signupBtn = findViewById(R.id.btn_signup)
        signinTv = findViewById(R.id.tv_sign_in)
        signupActionBar = findViewById(R.id.action_bar_signup)

        auth = Firebase.auth
        progressDialog = progressDialog(this@SignupActivity)
    }

    private fun addCallbacks() {

        signinTv.setOnClickListener {
            finish()
        }
        signupActionBar.setNavigationOnClickListener {
            finish()
        }
        signupBtn.setOnClickListener {
            registerUser(it)
        }
    }

    private fun validateRegisterDetails(): Boolean {
        var isValid = true

        //First Name
        if (TextUtils.isEmpty(firstNameEt.text.toString().trim { it <= ' ' })) {
            firstNameTI.error = resources.getString(R.string.enter_first_name)
            isValid = false
        } else {
            firstNameTI.error = null
        }

        //Last Name
        if (TextUtils.isEmpty(lastNameEt.text.toString().trim { it <= ' ' })) {
            lastNameTI.error = resources.getString(R.string.enter_last_name)
            isValid = false
        } else {
            lastNameTI.error = null
        }

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

        //Confirm Password
        if (TextUtils.isEmpty(passwordConfirmEt.text.toString().trim { it <= ' ' })) {
            passwordConfirmTI.error = resources.getString(R.string.enter_confirm_password)
            isValid = false
        } else if (passwordEt.text.toString()
                .trim { it <= ' ' } != passwordConfirmEt.text.toString().trim { it <= ' ' }
        ) {
            passwordConfirmTI.error =
                resources.getString(R.string.password_and_confirm_password_mismatch)
            isValid = false
        } else {
            passwordConfirmTI.error = null
        }

        //Check Terms
        if (!agreeTermsCb.isChecked) {
            agreeTermsErrorTv.visibility = View.VISIBLE
            isValid = false
        } else {
            agreeTermsErrorTv.visibility = View.INVISIBLE
        }

        return isValid
    }

    private fun registerUser(view: View) {
        //Check if the entries are valid or not
        if (validateRegisterDetails()) {
            val email = emailEt.text.toString().trim { it <= ' ' }
            val password = passwordEt.text.toString().trim { it <= ' ' }

            //Show progress dialog when entries are valid
            progressDialog.show()

            //Create an instance and create a register for a user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    //Hide progress dialog if registration is successful or not
                    progressDialog.dismiss()

                    if (task.isSuccessful) {
                        // Registration success, update UI with the signed-in user's information
                        showErrorSnackBar(
                            resources.getString(R.string.register_successful),
                            false,
                            view,
                            this@SignupActivity
                        )
                        //Move to Sign in activity to sign in
                        FirebaseAuth.getInstance().signOut()
                       //Back to Login after 2 sec
                        Handler().postDelayed({
                            finish()
                        }, 2000)
                        //Firebase registered user
                        val user = auth.currentUser

                        // updateUI(user)
                    } else {
                        // If registration fails, display a message to the user
                        showErrorSnackBar(
                            resources.getString(R.string.register_failed),
                            true,
                            view,
                            this@SignupActivity
                        )
                        //  updateUI(null)
                    }
                }
        }
    }
}
