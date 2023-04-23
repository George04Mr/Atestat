package com.georgedregan.atestatapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.georgedregan.atestatapp.R

class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val goToCreateAccountBtn = findViewById<Button>(R.id.goToCreateAccountBtn)
        goToCreateAccountBtn.setOnClickListener{goToCreateAccount()}
    }

    private fun goToCreateAccount() = startActivity(Intent(this, SignUpActivity::class.java))
}