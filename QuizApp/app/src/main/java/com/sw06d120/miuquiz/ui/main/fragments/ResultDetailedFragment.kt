package com.sw06d120.miuquiz.ui.main.fragments

import android.graphics.Color
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sw06d120.miuquiz.R
import com.sw06d120.miuquiz.classes.Answer
import com.sw06d120.miuquiz.database.entities.QuestionWithChoices
import com.sw06d120.miuquiz.models.QuizViewModel
import com.sw06d120.miuquiz.models.ResultDetailedViewModel
import kotlinx.android.synthetic.main.fragment_result.*
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
        for((index, question) in questions!!.withIndex()) {
            addQuestionSection(index + 1, question = question)
        }
    }

    fun addQuestionSection(index: Int, question: QuestionWithChoices) {
        val context = activity?.applicationContext!!

        val choices = question.choices
        var answers = getAnswersByQuestionsId(question.question.id)

        val questionSection = CardView(context)
        questionSection.setCardBackgroundColor(Color.WHITE)
        val questionInnerLayout = LinearLayout(context)
        questionInnerLayout.setBackgroundColor(Color.WHITE)
        questionInnerLayout.orientation = LinearLayout.VERTICAL
        questionSection.addView(questionInnerLayout)

        val txtQuestion = TextView(context)
        txtQuestion.textSize = 25f
        txtQuestion.typeface =
            activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_regular) }
        txtQuestion.text = "Question #${index} \n" + question.question.question
        questionInnerLayout.addView(txtQuestion)

        for(choice in choices) {
            val answerInnerLayout = LinearLayout(context)
            answerInnerLayout.orientation = LinearLayout.HORIZONTAL

            val checkPart = TextView(context)
            checkPart.text = if (choice.isCorrect) "☑ " else "□ "
            checkPart.textSize = 20f
            checkPart.setTextColor(0xFF689F38.toInt())
            checkPart.typeface =
                activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_light) }
            answerInnerLayout.addView(checkPart)

            val choiceQuestion = TextView(context)
            choiceQuestion.text = choice.answer
            choiceQuestion.textSize = 20f
            choiceQuestion.typeface =
                activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_light) }
            answerInnerLayout.addView(choiceQuestion)

            val answerRightLayout = LinearLayout(context)
            answerRightLayout.orientation = LinearLayout.HORIZONTAL
            answerRightLayout.gravity = Gravity.END
            answerInnerLayout.addView(answerRightLayout)

            if(answers.size > 0) {
                var answered = answers.filter { answer: Answer -> answer.answer.contains("[" + choice.id.toString() + "]") }.orEmpty()

                if(answered.size > 0) {
                    if(choice.isCorrect) {
                        for(perAnswer in answered) {
                            perAnswer.answer = perAnswer.answer.replace("[" + choice.id.toString() + "]", "")
                        }

                        val answeredChoice = TextView(context)
                        answeredChoice.text = "    CORRECT"
                        answeredChoice.gravity = Gravity.START
                        answeredChoice.setTextColor(0xFF009688.toInt())
                        answeredChoice.textSize = 20f
                        answeredChoice.typeface =
                            activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_light) }
                        answerRightLayout.addView(answeredChoice)
                    } else {
                        val answeredChoice = TextView(context)
                        answeredChoice.text = "    WRONG"
                        answeredChoice.gravity = Gravity.START
                        answeredChoice.setTextColor(0xFFD32F2F.toInt())
                        answeredChoice.textSize = 20f
                        answeredChoice.typeface =
                            activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_light) }
                        answerRightLayout.addView(answeredChoice)
                    }
                }
            } else if(choice.isCorrect) {
                val answeredChoice = TextView(context)
                answeredChoice.text = "    NO ANSWER"
                answeredChoice.gravity = Gravity.START
                answeredChoice.setTextColor(0xFFD32F2F.toInt())
                answeredChoice.textSize = 20f
                answeredChoice.typeface =
                    activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_light) }
                answerRightLayout.addView(answeredChoice)
            }

            questionInnerLayout.addView(answerInnerLayout)
        }

        val gap = TextView(context)
        gap.text = " "
        gap.textSize = 20f
        questionInnerLayout.addView(gap)

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