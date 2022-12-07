package com.example.customtheme

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder


class MainActivity : AppCompatActivity() {

    lateinit var preference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = getSharedPreferences("prefs", MODE_PRIVATE)
        applyTheme()
        setContentView(R.layout.activity_main)
        fillDetailTheme(findViewById(R.id.message))
        findViewById<View>(R.id.btn_change).setOnClickListener {
            val currentTheme = preference.getInt("theme", 1)
            preference.edit().putInt("theme", if (currentTheme == 1) 2 else 1).apply()
            TaskStackBuilder.create(this@MainActivity)
                .addNextIntent(Intent(this@MainActivity, MainActivity::class.java))
                .addNextIntent(this@MainActivity.intent)
                .startActivities()
        }
        val textView = findViewById<TextView>(R.id.message_with_color)
        val editText = findViewById<EditText>(R.id.edittext_with_color)
        var textViewState = false
        textView.isActivated = textViewState
        editText.isSelected = textViewState
        findViewById<View>(R.id.btn_change_color).setOnClickListener {
            textViewState = !textViewState
            textView.isActivated = textViewState
            editText.isSelected = textViewState
        }
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun applyTheme() {
        val theme = if (preference.getInt("theme", 1) == 1)
            com.example.theme.R.style.Theme1 else com.example.theme.R.style.Theme2
        setTheme(theme)
    }

    private fun fillDetailTheme(message: TextView) {
        val currentTheme = preference.getInt("theme", 1)
        val themeMessage = if (currentTheme == 1) "Current Theme is : 1" else "Current Theme is : 2"
        message.text = themeMessage
    }
}