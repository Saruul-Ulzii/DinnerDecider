package com.sw06d120.miuquiz.ui.main.fragments

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sw06d120.miuquiz.R
import com.sw06d120.miuquiz.classes.Answer
import com.sw06d120.miuquiz.database.entities.QuestionWithChoices
import com.sw06d120.miuquiz.models.QuizViewModel
import com.sw06d120.miuquiz.models.ResultDetailedViewModel
import kotlinx.android.synthetic.main.fragment_result_detailed.*
import kotlinx.android.synthetic.main.fragment_result_detailed.view.*

class ResultDetailedFragment : Fragment() {

    companion object {
        fun newInstance() = ResultDetailedFragment()
    }

    private lateinit var viewModel: ResultDetailedViewModel
    private val quizViewModel: QuizViewModel by activityViewModels()

    private var questions: List<QuestionWithChoices>? = null
    private var answers: List<Answer>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result_detailed, container, false)
        view.btnHomeFromAnalysis.setOnClickListener {
            quizViewModel.reset()
            Navigation.findNavController(view).navigate(R.id.homeFragment)
        }

        questions = quizViewModel.questions
        answers = quizViewModel.answers

        return view
    }

    fun createAnalysisView() {
        for(question in questions!!) {
            addQuestionSection(question = question)
        }
    }

    fun addQuestionSection(question: QuestionWithChoices) {
        val context = activity?.applicationContext!!

        var choices = question.choices
        var answers = getAnswersByQuestionsId(question.question.id)

        val questionSection = CardView(context)
        val questionInnerLayout = LinearLayout(context)
        questionInnerLayout.orientation = LinearLayout.VERTICAL
        questionSection.addView(questionInnerLayout)

        val txtQuestion = TextView(context)
        txtQuestion.textSize = 40f
        txtQuestion.text = question.question.question
        questionInnerLayout.addView(txtQuestion)

        for(choice in choices) {
            val answerInnerLayout = LinearLayout(context)
            answerInnerLayout.orientation = LinearLayout.HORIZONTAL

            val checkPart = TextView(context)
            checkPart.text = choice.isCorrect.toString()
            checkPart.textSize = 20f
            answerInnerLayout.addView(checkPart)

            val choiceQuestion = TextView(context)
            choiceQuestion.text = choice.answer
            choiceQuestion.textSize = 20f
            answerInnerLayout.addView(choiceQuestion)

            questionInnerLayout.addView(answerInnerLayout)
        }

        layoutQuestion.addView(questionSection)
    }

    fun getAnswersByQuestionsId(questionId: Long): List<Answer> {
        return answers?.filter { answer: Answer -> answer.questionId == questionId }.orEmpty()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResultDetailedViewModel::class.java)
        // TODO: Use the ViewModel
        createAnalysisView()
    }
}