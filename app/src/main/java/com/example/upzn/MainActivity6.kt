package com.example.upzn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main6)

        // Настройка кнопки "назад"
        val backButton = findViewById<ImageButton>(R.id.back_button18)
        backButton.setOnClickListener {
            finish()
        }

        // Настройка кнопки для открытия видео
        val button3 = findViewById<Button>(R.id.button12)
        button3.setOnClickListener {
            val videoUrl = "https://youtu.be/AfbBTQLxNfE?si=ZejTgZ_JUasVdhzz"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}