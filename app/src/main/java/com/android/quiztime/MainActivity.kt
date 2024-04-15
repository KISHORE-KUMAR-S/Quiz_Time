package com.android.quiztime

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStart = findViewById<Button>(R.id.startBtn)
        val etName = findViewById<EditText>(R.id.et_name)

        btnStart.setOnClickListener {
            when {
                etName.text.isEmpty() -> Toast.makeText(
                    this,
                    "Please enter your name",
                    Toast.LENGTH_LONG
                ).show()

                else -> {
                    val intent = Intent(this, QuestionsActivity::class.java)
                    intent.putExtra(Constants.USER_NAME, etName.text.toString())
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}