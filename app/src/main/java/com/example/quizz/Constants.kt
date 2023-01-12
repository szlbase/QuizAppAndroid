package com.example.quizz

import kotlin.collections.ArrayList

object Constants {

    const val USER_NAME: String = "user_name"
    const val SCORE: String = "user_score"

    fun getQuestions():ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val questionString = "What country does this flag belongs to?"

        val questionOne = Question(
            1, questionString,
            R.drawable.ic_flag_of_argentina,
            "Argentina","Australia","Singapore","USA",
            1
        )
        val questionTwo = Question(
            1, questionString,
            R.drawable.ic_flag_of_australia,
            "China","Australia","Mexico","Brazil",
            2
        )

        val questionThree = Question(
            1, questionString,
            R.drawable.ic_flag_of_belgium,
            "Switzerland","Korea","Philipine","Belgium",
            4
        )

        val questionFour = Question(
            1, questionString,
            R.drawable.ic_flag_of_brazil,
            "Denmark","Brazil","Russia","Japan",
            2
        )

        val questionFive = Question(
            1, questionString,
            R.drawable.ic_flag_of_denmark,
            "Kuwait","Mongolia","Malaysia","Denmark",
            4
        )

        val questionSix = Question(
            1, questionString,
            R.drawable.ic_flag_of_fiji,
            "Singapore","Thailand","Fiji","USA",
            3
        )

        val questionSeven = Question(
            1, questionString,
            R.drawable.ic_flag_of_germany,
            "Germany","Korea","Philipine","Belgium",
            1
        )

        val questionEight = Question(
            1, questionString,
            R.drawable.ic_flag_of_india,
            "Indonesia","India","Canada","Switzerland",
            2
        )

        val questionNine = Question(
            1, questionString,
            R.drawable.ic_flag_of_kuwait,
            "Singapore","Kuwait","China","Germany",
            2
        )

        val questionTen = Question(
            1, questionString,
            R.drawable.ic_flag_of_new_zealand,
            "USA","Switzerland","New Zealand","Australia",
            3
        )

        questionsList.add(questionOne)
        questionsList.add(questionTwo)
        questionsList.add(questionThree)
        questionsList.add(questionFour)
        questionsList.add(questionFive)
        questionsList.add(questionSix)
        questionsList.add(questionSeven)
        questionsList.add(questionEight)
        questionsList.add(questionNine)
        questionsList.add(questionTen)

        return questionsList
    }

//    private fun generateQuestions():ArrayList<Question> {
//         //TODO generate a set of questions from a set of images
//        val questionList = ArrayList<Question>()
//
//        val imageArray = arrayListOf(
//            R.drawable.ic_flag_of_argentina,
//            R.drawable.ic_flag_of_australia,
//            R.drawable.ic_flag_of_new_zealand,
//            R.drawable.ic_flag_of_belgium,
//            R.drawable.ic_flag_of_brazil,
//            R.drawable.ic_flag_of_denmark,
//            R.drawable.ic_flag_of_fiji,
//            R.drawable.ic_flag_of_germany,
//            R.drawable.ic_flag_of_india,
//            R.drawable.ic_flag_of_kuwait)
//
//        for (image in imageArray) {
//            val question = Question(
//                imageArray.indexOf(image),
//                "What Country Does this flag belongs to?",
//                image,
//            )
//        }
//    }

//    fun getCorrectAnswer(): String {
//        //TODO get correct answer based on image
//    }
}