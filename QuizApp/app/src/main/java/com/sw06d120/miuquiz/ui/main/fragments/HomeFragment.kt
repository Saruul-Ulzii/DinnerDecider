package com.sw06d120.miuquiz.ui.main.fragments

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
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
        view.btnStartQuiz.typeface =
            activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_regular) }
        view.btnStartQuiz.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.quizPrevFragment)
        }

        populateDatabase()

        return view
    }

    fun populateDatabase() {
        GlobalScope.launch {
            context?.let {val db = QuizDatabase(it)
                db.questionDao().deleteAll()
                db.choiceDao().deleteAll()
                db.answerDao().deleteAll()
                db.quizDao().deleteAll()

                var question1 = Question("What is a correct syntax to output \"Hello World\" in Kotlin?")
                var insertedQuestionId = db.questionDao().addQuestion(question1)

                var choice1 = Choice(0, insertedQuestionId, "println(\"Hello World\")", true, ChoiceType.One.toString())
                var choice2 = Choice(0, insertedQuestionId, "System.out.printline(\"Hello World\")", false, ChoiceType.One.toString())
                var choice3 = Choice(0, insertedQuestionId, "Console.WriteLine(\"Hello World\");", false, ChoiceType.One.toString())
                var choice4 = Choice(0, insertedQuestionId, "cout << \"Hello World\";", false, ChoiceType.One.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                question1 = Question("How do you insert COMMENTS in Kotlin code?")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "# This is a comment", false, ChoiceType.One.toString())
                choice2 = Choice(0, insertedQuestionId, "/* This is a comment", false, ChoiceType.One.toString())
                choice3 = Choice(0, insertedQuestionId, "-- This is a comment", false, ChoiceType.One.toString())
                choice4 = Choice(0, insertedQuestionId, "// This is a comment  ", true, ChoiceType.One.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                val question3 = Question("Kotlin was developed by ___")
                insertedQuestionId = db.questionDao().addQuestion(question3)

                val choice31 = Choice(0, insertedQuestionId, "JetBrains", true, ChoiceType.Text.toString())
                db.choiceDao().addChoice(choice31)

                question1 = Question("Which keyword is used to declare a function?")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "define", false, ChoiceType.One.toString())
                choice2 = Choice(0, insertedQuestionId, "fun  ", true, ChoiceType.One.toString())
                choice3 = Choice(0, insertedQuestionId, "func", false, ChoiceType.One.toString())
                choice4 = Choice(0, insertedQuestionId, "function", false, ChoiceType.One.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                question1 = Question("In Kotlin, code statements must end with a semicolon (;)")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "True", false, ChoiceType.One.toString())
                choice2 = Choice(0, insertedQuestionId, "False", true, ChoiceType.One.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)

                question1 = Question("Kotlin is a statically-typed programming language which runs on the ___")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "JVM", true, ChoiceType.Text.toString())
                db.choiceDao().addChoice(choice1)

                question1 = Question("Kotlin is interoperable with Java because it uses JVM bytecode.")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "Yes", true, ChoiceType.One.toString())
                choice2 = Choice(0, insertedQuestionId, "No", false, ChoiceType.One.toString())
                choice3 = Choice(0, insertedQuestionId, "Can be yes or no", false, ChoiceType.One.toString())
                choice4 = Choice(0, insertedQuestionId, "Can not say", false, ChoiceType.One.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                question1 = Question("How can you declare a variable in Kotlin?")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "var my_var: Char", true, ChoiceType.Many.toString())
                choice2 = Choice(0, insertedQuestionId, "var Char : my_var", false, ChoiceType.Many.toString())
                choice3 = Choice(0, insertedQuestionId, "my_var: Char", false, ChoiceType.Many.toString())
                choice4 = Choice(0, insertedQuestionId, "val my_var: Char", true, ChoiceType.Many.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                question1 = Question("All classes in Kotlin classes are by default?")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "public", false, ChoiceType.One.toString())
                choice2 = Choice(0, insertedQuestionId, "sealed", false, ChoiceType.One.toString())
                choice3 = Choice(0, insertedQuestionId, "abstract", false, ChoiceType.One.toString())
                choice4 = Choice(0, insertedQuestionId, "final", true, ChoiceType.One.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                question1 = Question("Which are the Kotlin keywords?")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "switch", false, ChoiceType.Many.toString())
                choice2 = Choice(0, insertedQuestionId, "when", true, ChoiceType.Many.toString())
                choice3 = Choice(0, insertedQuestionId, "arrayOf", true, ChoiceType.Many.toString())
                choice4 = Choice(0, insertedQuestionId, "LinkedTreeMap", false, ChoiceType.Many.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                question1 = Question("How many types of constructors available in Kotlin?")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "more than 1", true, ChoiceType.Many.toString())
                choice2 = Choice(0, insertedQuestionId, "4", false, ChoiceType.Many.toString())
                choice3 = Choice(0, insertedQuestionId, "2", true, ChoiceType.Many.toString())
                choice4 = Choice(0, insertedQuestionId, "11", false, ChoiceType.Many.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                question1 = Question("___ feature allows removing the risk of occurrence of NullPointerException in real time.")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "Null Safety", true, ChoiceType.Text.toString())
                db.choiceDao().addChoice(choice1)

                question1 = Question("Which of th following is used to compare two strings in Kotlin?")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "Using == operator", true, ChoiceType.Many.toString())
                choice2 = Choice(0, insertedQuestionId, "<", false, ChoiceType.Many.toString())
                choice3 = Choice(0, insertedQuestionId, "Using compareTo() extension function", true, ChoiceType.Many.toString())
                choice4 = Choice(0, insertedQuestionId, "None of the above", false, ChoiceType.Many.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)
                db.choiceDao().addChoice(choice4)

                question1 = Question("Elvis Operator is used for handling null expectations in Kotlin.")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "FALSE", false, ChoiceType.One.toString())
                choice2 = Choice(0, insertedQuestionId, "TRUE", true, ChoiceType.One.toString())
                choice3 = Choice(0, insertedQuestionId, "Can be true or false", false, ChoiceType.One.toString())

                db.choiceDao().addChoice(choice1)
                db.choiceDao().addChoice(choice2)
                db.choiceDao().addChoice(choice3)

                question1 = Question("What is kotlin file extension? Write it. (ex: exe, pdf)")
                insertedQuestionId = db.questionDao().addQuestion(question1)

                choice1 = Choice(0, insertedQuestionId, "kt", true, ChoiceType.Text.toString())
                db.choiceDao().addChoice(choice1)

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