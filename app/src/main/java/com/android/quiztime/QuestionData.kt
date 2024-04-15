package com.android.quiztime

data class QuestionData(
    val id: Int,
    val questionText: String,
    val imageResourceId: Int,
    val options: Array<String>,
    val correctAnswerIndex: Int
)
