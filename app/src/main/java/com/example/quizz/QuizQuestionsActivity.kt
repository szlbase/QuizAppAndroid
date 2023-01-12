package com.example.quizz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.coroutines.selects.select
import java.lang.reflect.Type
import kotlin.concurrent.thread
import kotlin.math.log

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var btnSubmit: Button? = null

    private var questionsList = loadQuestions()
    private val questionsCount = questionsList.size
    private var questionObj = getQuestion()
    private var selectedOption : Int = 0
    private var correctAnswer = 0

    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME)

        setUp()
    }

    private fun setUp() {
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.button_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        tvQuestion?.text = questionObj.question

        setQuestion()
    }

    private fun setQuestion() {
        setImage(questionObj.image)
        setProgress()
        setOptions()
    }

    private fun setOptions() {
        tvOptionOne?.text = questionObj.optionOne
        tvOptionTwo?.text = questionObj.optionTwo
        tvOptionThree?.text = questionObj.optionThree
        tvOptionFour?.text = questionObj.optionFour
    }

    private fun loadQuestions(): ArrayList<Question> {
        return Constants.getQuestions()
    }

    private fun getQuestion() : Question {
        val questionObj = questionsList.random()
        questionsList.remove(questionObj)
        return  questionObj
    }

    private fun nextQuestion() {
        questionObj = getQuestion()
        setQuestion()
        defaultOptionsView()
        defaultButton()
    }

    private fun setImage(image: Int) {
        ivImage?.setImageResource(image)
    }

    private fun setProgress() {
        val currentQuestionPosition = questionsCount - questionsList.size
        progressBar?.progress = currentQuestionPosition - 1
        tvProgress?.text = "$currentQuestionPosition/$questionsCount"
    }

    private fun defaultButton() {
        btnSubmit?.text = "submit"
    }

    private fun setButton() {
        if(questionsList.size == 0) {
            btnSubmit?.text = "FINISH"
        } else {
            btnSubmit?.text = "GO TO NEXT QUESTION"
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        tvOptionOne?.let { options.add(0, it) }
        tvOptionTwo?.let { options.add(1, it) }
        tvOptionThree?.let { options.add(2, it) }
        tvOptionFour?.let { options.add(3, it) }

        for(option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
            option.isClickable = true
        }
    }

    private fun enableOptionClickable(canClick:Boolean) {
        tvOptionOne?.isClickable = canClick
        tvOptionTwo?.isClickable = canClick
        tvOptionThree?.isClickable = canClick
        tvOptionFour?.isClickable = canClick
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        selectedOption = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this, drawableView)
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this, drawableView)
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this, drawableView)
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this, drawableView)
            }
        }
    }

    private fun btnSubmitPressed() {
        if(selectedOption == 0) {
            if(questionsList.size > 0) {
                nextQuestion()
            } else {
                displayResult()
            }
        } else {
            if(selectedOption != questionObj.correctAnswer) {
                answerView(selectedOption, R.drawable.wrong_option_border_bg)
            } else {
                correctAnswer += 1
            }
            answerView(questionObj.correctAnswer, R.drawable.correct_option_border_bg)
            setButton()
            selectedOption = 0
            enableOptionClickable(false)
        }
    }

    private fun displayResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(Constants.USER_NAME, userName)
        intent.putExtra(Constants.SCORE, correctAnswer.toString())
        startActivity(intent)
        finish()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.button_submit -> {
                btnSubmitPressed()
            }
        }
    }
}