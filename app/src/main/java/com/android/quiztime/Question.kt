package com.android.quiztime

data class Question(
    val id: Int,
    val questionText: String,
    val imageResourceId: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswerIndex: Int
)

