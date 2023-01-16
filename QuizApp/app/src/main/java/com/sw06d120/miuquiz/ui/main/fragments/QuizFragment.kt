package com.sw06d120.miuquiz.ui.main.fragments

import android.app.ActionBar.LayoutParams
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.icu.util.UniversalTimeScale.toLong
import android.os.Bundle
import android.text.TextUtils.split
import android.util.LayoutDirection
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.allViews
import androidx.core.view.children
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.sw06d120.miuquiz.R
import com.sw06d120.miuquiz.classes.Answer
import com.sw06d120.miuquiz.classes.ChoiceType
import com.sw06d120.miuquiz.database.QuizDatabase
import com.sw06d120.miuquiz.database.entities.QuestionWithChoices
import com.sw06d120.miuquiz.models.QuizViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlinx.android.synthetic.main.fragment_quiz.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private val viewModel: QuizViewModel by activityViewModels()
    private var currentQuestion: QuestionWithChoices? = null
    private var currentAnswer: Answer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        view.btnHome.setOnClickListener {
            viewModel.reset()
            Navigation.findNavController(view).navigate(R.id.homeFragment)
        }
        view.btnNext.setOnClickListener {
            checkAnswer()
            if (viewModel.currentIndex.value!! < viewModel.questions.size - 1) {
                viewModel.goNext()
                Navigation.findNavController(view).navigate(R.id.quizPrevFragment)
            } else {
                Navigation.findNavController(view).navigate(R.id.resultFragment)
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txtQuestionId.textSize = 35f
        txtQuestionId.typeface =
            activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_regular) }
        viewModel.currentIndex.observe(viewLifecycleOwner, Observer { currentQuestionId ->
            currentQuestion = viewModel.getCurrentQuestion()
            txtQuestionId.text = currentQuestion!!.question.question
            craftAnswers()
        })
    }

    fun craftAnswers() {
        val choiceType = currentQuestion?.choices?.get(0)?.type
        val context = activity?.applicationContext

        val radioButtonGroup = RadioGroup(context)
        radioButtonGroup.gravity = Gravity.CENTER_VERTICAL

        val colorList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),  // Disabled
                intArrayOf(android.R.attr.state_enabled)    // Enabled
            ),
            intArrayOf(
                0xFF344240.toInt(),
                0xFF009688.toInt()
            )
        )

        when (choiceType) {
            ChoiceType.One.toString() -> {
                for (choice in currentQuestion?.choices!!) {
                    val radioButton = RadioButton(context)
                    radioButton.text = choice.answer
                    radioButton.tag = choice.id
                    radioButton.buttonTintList = colorList
                    radioButton.textSize = 20f
                    radioButton.gravity = Gravity.CENTER_VERTICAL
//                    radioButton.height = LayoutParams.WRAP_CONTENT
//                    radioButton.buttonDrawable = StateListDrawable()
//                    radioButton.buttonDrawable = null
                    radioButton.typeface =
                        activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_light) }
                    radioButtonGroup.addView(radioButton)
                }

                layoutAnswers.addView(radioButtonGroup)
            }
            ChoiceType.Many.toString() -> {
                for (choice in currentQuestion?.choices!!) {
                    val checkBox = CheckBox(context)
                    checkBox.text = choice.answer
                    checkBox.tag = choice.id
                    checkBox.textSize = 20f
                    checkBox.gravity = Gravity.CENTER_VERTICAL
//                    checkBox.height = LayoutParams.WRAP_CONTENT
//                    checkBox.buttonDrawable = StateListDrawable()
//                    checkBox.buttonDrawable = null
                    checkBox.buttonTintList = colorList
                    checkBox.typeface =
                        activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_light) }
                    checkBox.isChecked = false

                    layoutAnswers.addView(checkBox)
                }
            }
            ChoiceType.Text.toString() -> {
                val textInput = EditText(context)
                textInput.height = 200
                textInput.tag = currentQuestion?.choices?.get(0)?.answer ?: "ANSWER MUST BE NOT NULL"
                textInput.textSize = 22f
                textInput.hint = "Write your answer"
                textInput.setHintTextColor(colorList)
                textInput.typeface =
                    activity?.let { ResourcesCompat.getFont(it.applicationContext, R.font.oswald_light) }
                textInput.id = currentQuestion?.choices?.get(0)?.id?.toInt() ?: 0
                layoutAnswers.addView(textInput)
            }
        }
    }

    fun checkAnswer() {
        val choiceType = currentQuestion?.choices?.get(0)?.type
        val answerViews = layoutAnswers.allViews

        when (choiceType) {
            ChoiceType.One.toString() -> {
                for (answerView in answerViews) {
                    if (answerView is RadioGroup) {
                        val answerRadioGroup = answerView as RadioGroup
                        for (radioButton in answerRadioGroup.children) {
                            if ((radioButton as RadioButton).isChecked) {
                                val choiceId = radioButton.tag.toString()
                                currentAnswer = Answer(
                                    currentQuestion?.question?.id!!,
                                    "[$choiceId]",
                                    isCorrectAnswer(choiceId)
                                )
                                break
                            }
                        }
                        break
                    }
                }
            }

            ChoiceType.Many.toString() -> {
                var answerFormatted = "" //1;3;5
                for (answerView in answerViews) {
                    if (answerView is CheckBox) {
                        val answerCheckBox = answerView as CheckBox
                        if (answerView.isChecked) {
                            val choiceId = answerCheckBox.tag.toString()
                            answerFormatted += "[$choiceId]";
                        }
                    }
                }

                if(answerFormatted != "") {
                    currentAnswer = Answer(
                        currentQuestion?.question?.id!!,
                        answerFormatted,
                        isCorrectAnswer(answerFormatted)
                    )
                }
            }
            ChoiceType.Text.toString() -> {
                for (answerView in answerViews) {
                    if (answerView is EditText) {
                        val answerEditText = answerView as EditText
                        val answerText = answerEditText.tag.toString()
                        if(answerText != "" && answerEditText.text.trim().toString().lowercase().equals(answerText.lowercase())) {
                            viewModel.addCorrectScore()
                            currentAnswer = Answer(
                                currentQuestion?.question?.id!!,
                                "[" + answerEditText.id.toString() + "]",
                                true
                            )
                        }
                    }
                }
            }
        }

        if (currentAnswer != null) {
            GlobalScope.launch {
                context?.let {
                    val currentAnswerEntity = com.sw06d120.miuquiz.database.entities.Answer(
                        currentAnswer!!
                    )
                    QuizDatabase(it).answerDao().addAnswer(currentAnswerEntity)
                    viewModel.addAnswer(currentAnswer!!)
                }
            }
        }
    }

    fun isCorrectAnswer(selectedChoice: String): Boolean {
        if(selectedChoice.equals("")) return false

        val choiceType = currentQuestion?.choices?.get(0)?.type

        when (choiceType) {
            ChoiceType.One.toString() -> {
                for (choice in currentQuestion?.choices!!) {
                    if (choice.isCorrect && choice.id.equals(selectedChoice.replace("[","").replace("]","").toLong())) {
                        viewModel.addCorrectScore()
                        return true
                    }
                }
                return false
            }
            ChoiceType.Many.toString() -> {
                var answers = selectedChoice.replace("[","").split("]")
                var correctCount = 0

                for(answer in answers) {
                    if(answer != "") {
                        for (choice in currentQuestion?.choices!!) {
                            if(choice.isCorrect && choice.id.equals(answer.toLong())) {
                                correctCount++
                                break
                            }
                        }
                    }
                }

                if(correctCount == answers.size - 1) {
                    viewModel.addCorrectScore()
                    return true
                } else {
                    return false
                }
            }
        }
        return false
    }
}
