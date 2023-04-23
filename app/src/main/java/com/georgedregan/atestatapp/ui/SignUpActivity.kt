package com.georgedregan.atestatapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.georgedregan.atestatapp.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val goToSignInBtn = findViewById<Button>(R.id.goToSignIn)
        goToSignInBtn.setOnClickListener { goToSignIn() }

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {

                    // Check if user is logged in
                    // ToDo Check from DB
                    return if (true) {
                        // The content is ready. Start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                        false
                    }
                }
            }
        )
    }

    private fun goToSignIn() = startActivity(Intent(this, SignInActivity::class.java))
}