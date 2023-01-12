package com.example.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var etName : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart : Button = findViewById(R.id.startButton)
        etName = findViewById(R.id.et_name)

        buttonStart.setOnClickListener {
            buttonClicked(etName?.text!!.isEmpty())
        }
    }

    private fun buttonClicked(isTextFieldEmpty: Boolean) {
        if (isTextFieldEmpty) {
            Toast.makeText(this, "Please enter your name!", Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, etName?.text.toString())
            startActivity(intent)
        }
    }
}