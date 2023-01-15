package com.sw06d120.miuquiz.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.sw06d120.miuquiz.R
import com.sw06d120.miuquiz.classes.ChoiceType
import com.sw06d120.miuquiz.database.entities.Choice
import com.sw06d120.miuquiz.models.QuizViewModel
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlinx.android.synthetic.main.fragment_quiz.view.*

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private val viewModel: QuizViewModel by activityViewModels()
    private var choices: List<Choice>? = null

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
            txtQuestionId.text = viewModel.getCurrentQuestion().question.question
            choices = viewModel.getCurrentQuestion().choices
            craftAnswers()
        })
    }

    fun craftAnswers() {
        var choiceType = choices?.get(0)?.type
        var context = activity?.applicationContext

        var radioButtonGroup = RadioGroup(context)

        when (choiceType) {
            ChoiceType.One.toString() -> {
                for (choice in choices!!) {
                    print("ONE")
                    val radioButton = RadioButton(context)
                    radioButton.text = choice.answer
                    radioButtonGroup.addView(radioButton)
                }

                layoutAnswers.addView(radioButtonGroup)
            }
            ChoiceType.Many.toString() -> {
                print("MANY")
                for (choice in choices!!) {
                    val checkBox = CheckBox(context)
                    checkBox.text = choice.answer
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
}