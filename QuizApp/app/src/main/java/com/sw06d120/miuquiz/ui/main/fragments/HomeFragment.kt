package com.sw06d120.miuquiz.ui.main.fragments

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.sw06d120.miuquiz.R
import com.sw06d120.miuquiz.classes.ChoiceType
import com.sw06d120.miuquiz.database.QuizDatabase
import com.sw06d120.miuquiz.database.entities.Choice
import com.sw06d120.miuquiz.database.entities.ChoiceAndQuestion
import com.sw06d120.miuquiz.database.entities.Question
import com.sw06d120.miuquiz.models.QuizViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: QuizViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.btnStartQuiz.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.quizPrevFragment)
        }

        populateDatabase()

        return view
    }

    fun populateDatabase() {
        GlobalScope.launch {
            context?.let {
                var db = QuizDatabase(it)
                db.questionDao().deleteAll()
                db.choiceDao().deleteAll()
                db.answerDao().deleteAll()
                db.quizDao().deleteAll()

                var question1 = Question("Kotlin question 1")
                var insertedQuestionId = db.questionDao().addQuestion(question1)

                var choice1 = Choice(0, insertedQuestionId, "Answer 1", false, ChoiceType.One.toString())
                var choice2 = Choice(0, insertedQuestionId, "Answer 2", true, ChoiceType.One.toString())
                var choice3 = Choice(0, insertedQuestionId, "Answer 3", false, ChoiceType.One.toString())
                var choice4 = Choice(0, insertedQuestionId, "Answer 4", false, ChoiceType.One.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                var question2 = Question("Kotlin question 2")
                insertedQuestionId = db.questionDao().addQuestion(question2)

                var choice21 = Choice(0, insertedQuestionId, "Answer 2-1", true, ChoiceType.Many.toString())
                var choice22 = Choice(0, insertedQuestionId, "Answer 2-2", false, ChoiceType.Many.toString())
                var choice23 = Choice(0, insertedQuestionId, "Answer 2-3", false, ChoiceType.Many.toString())
                var choice24 = Choice(0, insertedQuestionId, "Answer 2-4", true, ChoiceType.Many.toString())

                db.choiceDao().addChoice(choice21)
                db.choiceDao().addChoice(choice22)
                db.choiceDao().addChoice(choice23)
                db.choiceDao().addChoice(choice24)

                var question3 = Question("Kotlin question 3")
                insertedQuestionId = db.questionDao().addQuestion(question3)

                var choice31 = Choice(0, insertedQuestionId, "Answer 3-1", true, ChoiceType.Text.toString())
                var choice32 = Choice(0, insertedQuestionId, "Answer 3-2", false, ChoiceType.Text.toString())
                var choice33 = Choice(0, insertedQuestionId, "Answer 3-3", false, ChoiceType.Text.toString())
                var choice34 = Choice(0, insertedQuestionId, "Answer 3-4", true, ChoiceType.Text.toString())

                db.choiceDao().addChoice(choice31)
                db.choiceDao().addChoice(choice32)
                db.choiceDao().addChoice(choice33)
                db.choiceDao().addChoice(choice34)

                print(db.choiceDao().getAllChoices())

//                var questions = db.questionDao().getAllQuestions()
//
//                for (question in questions) {
//                    var
//                }

                viewModel.questions = db.questionDao().getQuestionWithChoices()
            }
        }
    }

    fun startQuiz(view: View) {
//        Nav
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}