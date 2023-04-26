package com.georgedregan.atestatapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.georgedregan.atestatapp.R
import com.georgedregan.atestatapp.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var passwordET: EditText
    private lateinit var usernameET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val goToCreateAccountBtn = findViewById<Button>(R.id.goToCreateAccountBtn)
        val signInBtn = findViewById<Button>(R.id.signInBtn)
        passwordET = findViewById(R.id.passwordET)
        usernameET = findViewById(R.id.usernameET)

        goToCreateAccountBtn.setOnClickListener { goToSignUpActivityActivity() }
        signInBtn.setOnClickListener { signIn() }
    }

    private fun goToSignUpActivityActivity() {
        startActivity(Intent(this, SignUpActivity::class.java))
        finishAffinity()
    }

    private fun signIn() {
        val password = passwordET.text.toString()
        val username = usernameET.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val userDao = db.userDao()
            val user = userDao.getUser(username)

            if (password == user?.password) {
                userDao.insert(user.copy(isLoggedIn = true))
                goToMainActivity()
            } else {
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@SignInActivity,
                        "Ai greșit parola sau numele de utilizator, încearcă din nou",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        finishAffinity()
    }
}