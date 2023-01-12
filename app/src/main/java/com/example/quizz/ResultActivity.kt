package com.example.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private var userName : String? = null
    private var correctAnswers : String? = null

    private var tvName: TextView? = null
    private var tvScore: TextView? = null
    private var btnFinish: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        userName = intent.getStringExtra(Constants.USER_NAME)
        correctAnswers = intent.getStringExtra(Constants.SCORE)

        tvName = findViewById(R.id.tv_name)
        tvScore = findViewById(R.id.tv_score)
        btnFinish = findViewById(R.id.btn_finish)

        tvName?.text = userName
        tvScore?.text = "Your Score is $correctAnswers out of 10!"

        btnFinish?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}