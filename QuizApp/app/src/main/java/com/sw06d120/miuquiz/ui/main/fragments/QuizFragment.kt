package com.sw06d120.miuquiz.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.allViews
import androidx.core.view.children
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
        print("entered new quiz!")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.currentIndex.observe(viewLifecycleOwner, Observer { currentQuestionId ->
            currentQuestion = viewModel.getCurrentQuestion()
            txtQuestionId.text = currentQuestion!!.question.question
            craftAnswers()
        })
    }

    fun craftAnswers() {
        var choiceType = currentQuestion?.choices?.get(0)?.type
        var context = activity?.applicationContext

        var radioButtonGroup = RadioGroup(context)

        when (choiceType) {
            ChoiceType.One.toString() -> {
                for (choice in currentQuestion?.choices!!) {
                    print("ONE")
                    val radioButton = RadioButton(context)
                    radioButton.text = choice.answer
                    radioButton.tag = choice.id
                    radioButtonGroup.addView(radioButton)
                }

                layoutAnswers.addView(radioButtonGroup)
            }
            ChoiceType.Many.toString() -> {
                print("MANY")
                for (choice in currentQuestion?.choices!!) {
                    val checkBox = CheckBox(context)
                    checkBox.text = choice.answer
                    checkBox.tag = choice.id
                    checkBox.isChecked = false

                    layoutAnswers.addView(checkBox)
                }
            }
            ChoiceType.Text.toString() -> {
                var textInput = EditText(context)
                textInput.height = 200
                layoutAnswers.addView(textInput)
            }
        }
    }

    fun checkAnswer() {
        val choiceType = currentQuestion?.choices?.get(0)?.type

        when (choiceType) {
            ChoiceType.One.toString() -> {
                var answerViews = layoutAnswers.allViews

                for (answerView in answerViews) {
                    if (answerView is RadioGroup) {
                        var answerRadioGroup = answerView as RadioGroup
                        for (radioButton in answerRadioGroup.children) {
                            if ((radioButton as RadioButton).isChecked) {
                                currentAnswer = Answer(
                                    currentQuestion?.question?.id!!,
                                    radioButton.tag.toString(),
                                    isCorrectAnswer(radioButton.tag.toString())
                                )
                                break
                            }
                        }
                        break
                    }
                }
            }

            ChoiceType.Many.toString() -> {

            }
            ChoiceType.Text.toString() -> {

            }
        }

        if (currentAnswer != null) {
            print("CURRENT ANSWER")
            print(currentAnswer)
            GlobalScope.launch {
                context?.let {
                    var currentAnswerEntity = com.sw06d120.miuquiz.database.entities.Answer(
                        currentAnswer!!
                    )
                    QuizDatabase(it).answerDao().addAnswer(currentAnswerEntity)
                }
            }
        }
    }

    fun isCorrectAnswer(selectedChoice: String): Boolean {
        val choiceType = currentQuestion?.choices?.get(0)?.type

        when (choiceType) {
            ChoiceType.One.toString() -> {
                for (choice in currentQuestion?.choices!!) {
                    if (choice.isCorrect && choice.id.equals(selectedChoice.toLong())) {
                        viewModel.addCorrectScore()
                        return true
                    }
                }
                return false
            }
            ChoiceType.Many.toString() -> {
                print("MANY")
//                for (choice in currentQuestion?.choices!!) {
//                    if(choice.isCorrect && choice.answer == selectedChoice) {
//                        return true
//                    }
//                }
//                return false
            }
            ChoiceType.Text.toString() -> {
                print("TEXT")
//                var textInput = EditText(context)
//                textInput.height = 200
//                layoutAnswers.addView(textInput)
            }
        }
        return false
    }
}
