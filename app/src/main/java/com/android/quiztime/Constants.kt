package com.android.quiztime

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val questionData = listOf(
            QuestionData(
                id = 1,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_argentina,
                options = arrayOf("Argentina", "Australia", "Armenia", "Australia"),
                correctAnswerIndex = 0
            ),
            QuestionData(
                id = 2,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_australia,
                options = arrayOf("Angola", "Austria", "Australia", "Armenia"),
                correctAnswerIndex = 2
            ),
            QuestionData(
                id = 3,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_brazil,
                options = arrayOf("Belarus", "Belize", "Brunei", "Brazil"),
                correctAnswerIndex = 3
            ),
            QuestionData(
                id = 4,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_belgium,
                options = arrayOf("Bahamas", "Belgium", "Barbados", "Belize"),
                correctAnswerIndex = 1
            ),
            QuestionData(
                id = 5,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_fiji,
                options = arrayOf("Gabon", "France", "Fiji", "Finland"),
                correctAnswerIndex = 2
            ),
            QuestionData(
                id = 6,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_germany,
                options = arrayOf("Germany", "Georgia", "Greece", "none of these"),
                correctAnswerIndex = 0
            ),
            QuestionData(
                id = 7,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_denmark,
                options = arrayOf("Dominica", "Egypt", "Denmark", "Ethiopia"),
                correctAnswerIndex = 2
            ),
            QuestionData(
                id = 8,
                questionText = "What country does this flag belong to?",
                imageResourceId =  R.drawable.ic_flag_of_india,
                options = arrayOf("Ireland", "Iran", "Hungary", "India"),
                correctAnswerIndex = 3
            ),
            QuestionData(
                id = 9,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_new_zealand,
                options = arrayOf("Australia", "New Zealand", "Tuvalu", "United States of America"),
                correctAnswerIndex = 1
            ),
            QuestionData(
                id = 10,
                questionText = "What country does this flag belong to?",
                imageResourceId = R.drawable.ic_flag_of_kuwait,
                options = arrayOf("Kuwait", "Jordan", "Sudan", "Palestine"),
                correctAnswerIndex = 0
            )
        )

        for (data in questionData) {
            val question = Question(
                id = data.id,
                questionText = data.questionText,
                imageResourceId = data.imageResourceId,
                optionOne = data.options[0],
                optionTwo = data.options[1],
                optionThree = data.options[2],
                optionFour = data.options[3],
                correctAnswerIndex = data.correctAnswerIndex + 1 // Add 1 because index starts from 0
            )
            questionsList.add(question)
        }

        return questionsList
    }
}