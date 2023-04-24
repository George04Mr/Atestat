package com.georgedregan.atestatapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.georgedregan.atestatapp.R
import com.georgedregan.atestatapp.data.User
import com.georgedregan.atestatapp.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private val userList = mutableListOf<User>()
    private lateinit var nameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var usernameET: EditText

    private var isLoggedInUser = false
    private var isDataReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val goToSignInBtn = findViewById<Button>(R.id.goToSignIn)
        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        nameET = findViewById(R.id.nameET)
        passwordET = findViewById(R.id.passwordET)
        usernameET = findViewById(R.id.usernameET)

        goToSignInBtn.setOnClickListener { goToSignInActivity() }
        signUpBtn.setOnClickListener { signUp() }

        getAllUsers()

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {

                    return if (isDataReady) {
                        // The content is ready. Start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        if (isLoggedInUser) {
                            goToMainActivity()
                            false
                        } else {
                            true
                        }
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun getAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val userDao = db.userDao()
            isLoggedInUser = userDao.getLoggedInUser() != null
            isDataReady = true
        }
    }

    private fun goToSignInActivity() {
        startActivity(Intent(this, SignInActivity::class.java))
        finishAffinity()
    }

    private fun signUp() {
        val name = nameET.text.toString()
        val password = passwordET.text.toString()
        val username = usernameET.text.toString()
        val user = User(
            username = username,
            name = name,
            password = password,
            isLoggedIn = true
        )

        CoroutineScope(Dispatchers.IO).launch {
            val userDao = db.userDao()
            userDao.insert(user)
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
        finishAffinity()
    }
}