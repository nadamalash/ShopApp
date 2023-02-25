package com.malash.shopapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout
import com.malash.shopapp.R
import com.malash.shopapp.utils.showErrorSnackBar

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initView()
        addCallbacks()
    }

    private fun initView() {
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
    }

    private fun addCallbacks() {

        signinTv.setOnClickListener {
            onBackPressed()
        }
        signupActionBar.setNavigationOnClickListener {
            onBackPressed()
        }
        signupBtn.setOnClickListener {
            validateRegisterDetails(it)
        }
    }

    private fun validateRegisterDetails(view:View){
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
        } else if (passwordEt.text.toString() != passwordConfirmEt.text.toString()) {
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
        //If is valid
        if (isValid){
            showErrorSnackBar(resources.getString(R.string.register_successfully),false,view,this@SignupActivity )
        }
    }
}
