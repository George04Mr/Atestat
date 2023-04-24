package com.georgedregan.atestatapp.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.georgedregan.atestatapp.R
import com.georgedregan.atestatapp.data.GlucoseLevel
import com.georgedregan.atestatapp.data.User
import com.georgedregan.atestatapp.database.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { HomeListAdapter() }
    private lateinit var db: AppDatabase
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val contentRV = findViewById<RecyclerView>(R.id.contentRV)
        val addGlucoseLevelFab = findViewById<FloatingActionButton>(R.id.addGlucoseLevelFab)
        val logoutIV = findViewById<ImageView>(R.id.logoutIV)

        contentRV.adapter = adapter
        getUser()

        addGlucoseLevelFab.setOnClickListener { openAddGlucoseLevelDialog() }
        logoutIV.setOnClickListener { logout() }
    }

    private fun getUser() {
        CoroutineScope(IO).launch {
            val userDao = db.userDao()
            user = userDao.getLoggedInUser()
            getGlucoseLevelList()
        }
    }

    private fun getGlucoseLevelList() {
        CoroutineScope(IO).launch {
            val glucoseLevelDao = db.glucoseLevelDao()
            val glucoseLevelList = glucoseLevelDao.getAll(user?.username.orEmpty())
            adapter.submitList(glucoseLevelList)
        }
    }

    private fun openAddGlucoseLevelDialog() {
        Dialog(this).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val view = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.dialog_add_glucose_level, null)

            val glucoseLevelET = view.findViewById<EditText>(R.id.glucoseLevelET)
            val cancelBtn = view.findViewById<Button>(R.id.cancelBtn)
            val addBtn = view.findViewById<Button>(R.id.addBtn)

            cancelBtn.setOnClickListener { dismiss() }
            addBtn.setOnClickListener {
                onAddBtnClicked(glucoseLevelET.text.toString().toInt())
                dismiss()
            }

            setContentView(view)
            show()
        }
    }

    private fun logout() {
        CoroutineScope(IO).launch {
            val userDao = db.userDao()
            userDao.insert(user!!.copy(isLoggedIn = false))
            goToSignInActivity()
        }
    }

    private fun goToSignInActivity() {
        startActivity(Intent(this, SignInActivity::class.java))
        finishAffinity()
    }

    private fun onAddBtnClicked(glucoseLevel: Int) {
        val glucoseLevelDao = db.glucoseLevelDao()
        CoroutineScope(IO).launch {
            val glucoseLevelObject = GlucoseLevel(
                date = System.currentTimeMillis(),
                userName = user!!.username,
                level = glucoseLevel
            )
            glucoseLevelDao.insert(glucoseLevelObject)
            getGlucoseLevelList()
        }
    }
}