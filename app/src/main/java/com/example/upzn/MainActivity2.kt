package com.example.upzn

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private lateinit var myImageButton: ImageButton

    private lateinit var imageButton: ImageButton
    private lateinit var imageButton1: ImageButton
    private lateinit var imageButton2: ImageButton
    private lateinit var imageButton21: ImageButton
    private lateinit var imageButton3: ImageButton
    private lateinit var imageButton31: ImageButton
    private lateinit var textView: TextView
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView21: TextView
    private lateinit var textView3: TextView
    private lateinit var textView31: TextView
    private lateinit var ImageView: ImageView



    private var count: Int = 5
    private var countd: Int = 55
    private var count1: Int = 52
    private var countd1: Int = 6
    private var count2: Int = 52
    private var countd21: Int = 6

    private var buttonState: Int = 0
    private var buttonStaterep: Int = 0
    private var buttonState2: Int = 0
    private var buttonStaterep2: Int = 0
    private var buttonState3: Int = 0
    private var buttonStaterep3: Int = 0

    private lateinit var textView5: TextView
    private lateinit var textViewPOLN: TextView
    private var currentTextState: Int = 0

    private val texts = listOf("Программистам и другим профессионалам! Подписывайтесь на этот бесплатный курс!")
    private val texts2 = listOf("Мы собрали самые интересные факты для начала карьеры в мире информационных технологий. Скорее к нам!!!")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)




        myImageButton = findViewById(R.id.imageButton29)

        myImageButton.setOnClickListener {
            // Создаем Intent для перехода в другую Activity (SecondActivity)
            val intent = Intent(this, MainActivity2::class.java)

            // Запускаем другую Activity
            startActivity(intent)}

        imageButton = findViewById(R.id.imageButton )
        imageButton1 = findViewById(R.id.imageButton2 )
        imageButton2 = findViewById(R.id.imageButton4 )
        imageButton21 = findViewById(R.id.imageButton9 )
        imageButton3 = findViewById(R.id.imageButton10 )
        imageButton31 = findViewById(R.id.imageButton11 )
        textView = findViewById(R.id.textView11)
        textView1 = findViewById(R.id.textView12)
        textView2 = findViewById(R.id.textView16)
        textView21 = findViewById(R.id.textView17)
        textView3 = findViewById(R.id.textView19)
        textView31 = findViewById(R.id.textView20)

        //pokazati polnostiy
        textView5 = findViewById(R.id.textView5)

        textView5.setOnClickListener {
            onTextViewClicked()
            updateText()
        }
        //



        //pokazati polnostiy
        textViewPOLN = findViewById(R.id.textView10)

        textViewPOLN.setOnClickListener {
            onTextViewClicked1()
            updateText1()
        }
        //

        updateUI()
        //лайки
        imageButton.setOnClickListener {
            onImageButtonClicked()
        }
        imageButton2.setOnClickListener {
            onImageButtonClicked2()
        }
        imageButton3.setOnClickListener {
            onImageButtonClicked3()
        }
        ///


        //репосты
        imageButton1.setOnClickListener {
            onImageButtomClicked1()
        }
        imageButton21.setOnClickListener {
            onImageButtomClicked2()
        }
        imageButton31.setOnClickListener {
            onImageButtomClicked3()
        }
        //


    }
    //лайки
    private fun onImageButtonClicked() {
        if (buttonState == 0) {
            count++
        } else {
            count--
        }
        buttonState = (buttonState + 1) % 2
        updateUI()
    }

    //показать полностью
    private fun onTextViewClicked() {
        currentTextState = (currentTextState + 1) % texts.size // Переключаемся по кругу
        updateText()
    }

    private fun updateText() {
        textView5.text = texts[currentTextState] // Устанавливаем текст из списка
    }

    private fun onTextViewClicked1() {
        currentTextState = (currentTextState+ 1) % texts2.size // Переключаемся по кругу
        updateText1()
    }

    private fun updateText1() {
        textViewPOLN.text = texts2[currentTextState] // Устанавливаем текст из списка
    }
    //
    //лайки
    private fun onImageButtonClicked2() {
        if (buttonState2 == 0) {
            count1++
        }
        else {
            count1--
        }
        buttonState2 = (buttonState2 + 1) % 2
        updateUI2()
    }
    private fun onImageButtonClicked3() {
        if (buttonState3 == 0) {
            count2++
        } else {
            count2--
        }
        buttonState3 = (buttonState3 + 1) % 2
        updateUI3()
    }
    //

    //репосты
    private  fun onImageButtomClicked1(){

        countd++

        buttonStaterep = (buttonStaterep + 1) % 2
        textView1.text = formatCount(countd)
    }
    private  fun onImageButtomClicked2(){

        countd1++

        buttonStaterep2 = (buttonStaterep2 + 1) % 2
        textView21.text = formatCount(countd1)
    }
    private  fun onImageButtomClicked3(){

        countd21++

        buttonStaterep3 = (buttonStaterep3 + 1) % 2
        textView31.text = formatCount(countd21)
    }
    //

    //для лайков
    private fun updateUI() {
        when (buttonState) {
            0 -> {
                imageButton.setImageResource(R.drawable.like)
            }
            else -> {
                imageButton.setImageResource(R.drawable.likered)
            }
        }
        textView.text = formatCount(count)
    }
    private fun updateUI2() {
        when (buttonState2) {
            0 -> {
                imageButton2.setImageResource(R.drawable.like)
            }
            else -> {
                imageButton2.setImageResource(R.drawable.likered)
            }
        }
        textView2.text = formatCount(count1)
    }
    private fun updateUI3() {
        when (buttonState3) {
            0 -> {
                imageButton3.setImageResource(R.drawable.like)
            }
            else -> {
                imageButton3.setImageResource(R.drawable.likered)

            }
        }
        textView3.text = formatCount(count2)
    }
    //

    //формат по типу 1к, 10к, 1м
    private fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> {
                val millions = count / 1_000_000.0
                if (millions >= 10) {
                    "${millions.toInt()}M"
                } else {
                    String.format("%.1fM", millions)
                }
            }
            count >= 10_000 -> {
                "${count / 1_000}K"
            }
            count >= 1100 -> {
                "${count / 1000.0}K"
            }
            count > 999 -> {
                "1K"
            }
            else -> {
                count.toString()
            }
        }
    }
}