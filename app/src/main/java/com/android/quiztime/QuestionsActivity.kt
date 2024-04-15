package com.android.quiztime

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionsActivity : AppCompatActivity(), OnClickListener {
    private var currentPosition = 1
    private var questionsList: ArrayList<Question>? = null
    private var selectedOption: Int = 0
    private var userName: String? = null
    private var correctAnswers: Int = 0

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptions: ArrayList<TextView?>? = null
    private var btnSubmit: Button? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptions = arrayListOf(
            findViewById(R.id.tv_option_one),
            findViewById(R.id.tv_option_two),
            findViewById(R.id.tv_option_three),
            findViewById(R.id.tv_option_four)
        )
        btnSubmit = findViewById(R.id.btn_submit)

        tvOptions?.forEach { it?.setOnClickListener(this) }
        btnSubmit?.setOnClickListener(this)

        questionsList = Constants.getQuestions()

        setQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val question: Question = questionsList!![currentPosition - 1]
        val max = Integer.parseInt(progressBar?.max.toString()) + 1

        defaultOptionsView()

        progressBar?.progress = currentPosition
        tvProgress?.text = "$currentPosition / $max"
        ivImage?.setImageResource(question.imageResourceId)
        tvQuestion?.text = question.questionText
        tvOptions!![0]?.text = question.optionOne
        tvOptions!![1]?.text = question.optionTwo
        tvOptions!![2]?.text = question.optionThree
        tvOptions!![3]?.text = question.optionFour

        btnSubmit!!.text = if (currentPosition == questionsList!!.size) "Finish" else "Submit"
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        for(i in 0 until tvOptions!!.size) {
            tvOptions!![i].let { options.add(0, it!!) }
        }

        tvOptions?.forEach {
            it?.setTextColor(Color.parseColor("#7A8089"))
            it?.typeface = Typeface.DEFAULT
            it?.background = ContextCompat.getDrawable(this, R.drawable.option_border)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionPosition: Int) {
        defaultOptionsView()

        selectedOption = selectedOptionPosition

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option_one -> tvOptions!![0]?.let { selectedOptionView(it, 1) }
            R.id.tv_option_two -> tvOptions!![1]?.let { selectedOptionView(it, 2) }
            R.id.tv_option_three -> tvOptions!![2]?.let { selectedOptionView(it, 3) }
            R.id.tv_option_four -> tvOptions!![3]?.let { selectedOptionView(it, 4) }
            R.id.btn_submit -> when (selectedOption) {
                0 -> {
                    currentPosition += 1
                    when {
                        currentPosition <= questionsList!!.size -> setQuestion()
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)

                            intent.putExtra(Constants.USER_NAME, userName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else -> {
                    val question = questionsList?.get(currentPosition-1)

                    when {
                        question!!.correctAnswerIndex != selectedOption -> answerView(selectedOption, R.drawable.wrong_option_border)
                        else -> {
                            answerView(selectedOption, R.drawable.correct_option_border)
                            correctAnswers++
                        }
                    }
                    answerView(question.correctAnswerIndex, R.drawable.correct_option_border)

                    when (currentPosition) {
                        questionsList!!.size -> btnSubmit?.text = "Finish"
                        else -> btnSubmit?.text = "Next Question"
                    }

                    selectedOption = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        tvOptions!![answer - 1]?.apply {
            background = ContextCompat.getDrawable(this@QuestionsActivity, drawableView)
            setTextColor(if (drawableView == R.drawable.correct_option_border) Color.BLACK else Color.WHITE)
        }
    }
}